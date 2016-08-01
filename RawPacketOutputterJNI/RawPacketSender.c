/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include "RawPacketSender.h"
#include <stdio.h>
#include <jni.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <net/if.h>
#include <netinet/ip.h>
#include <netinet/udp.h>
#include <netinet/ether.h>
#include <linux/if_packet.h>
#include <string.h>
#include <sys/ioctl.h>


#define MY_DEST_MAC0 0xFF
#define MY_DEST_MAC1 0xFF
#define MY_DEST_MAC2 0xFF
#define MY_DEST_MAC3 0xFF
#define MY_DEST_MAC4 0xFF
#define MY_DEST_MAC5 0xFF

JNIEXPORT void JNICALL Java_packetmaker_RawPacketSender_sendPacket
  (JNIEnv *env, jobject obj, jbyteArray buffer){
    printf("Sending data...\n");
    //note that jsize is just an int!
   
    jsize len = (*env)->GetArrayLength(env, buffer);
    jbyte *bufferPtr = (*env)->GetByteArrayElements(env, buffer, 0);
    printf("ArrayLength is: %d\n", len);
    printf("Array data is: ");
    for (int i=0; i<len; i++) {
        printf("%c",bufferPtr[i]);
    }
    sendData(len, bufferPtr);
    (*env)->ReleaseByteArrayElements(env, buffer, bufferPtr, 0);   
}


int sockfd;
int sendData(int size, char buffer[]){

    if ((sockfd = socket(AF_PACKET, SOCK_RAW, IPPROTO_RAW)) == -1) {
        perror("socket");
    }

//GET INTERFEACE INDEX
    struct ifreq if_idx;
    memset(&if_idx, 0, sizeof(struct ifreq));
    strncpy(if_idx.ifr_name, "enp0s3", IFNAMSIZ-1);
    if (ioctl(sockfd, SIOCGIFINDEX, &if_idx) < 0)
        perror("SIOCGIFINDEX");

//GET AND PRINT MAC ADDRESS
    struct ifreq if_mac;
    memset(&if_mac, 0, sizeof(struct ifreq));
    strncpy(if_mac.ifr_name, "enp0s3", IFNAMSIZ-1);
    if (ioctl(sockfd, SIOCGIFHWADDR, &if_mac) < 0)
        perror("SIOCGIFHWADDR");
    const unsigned char* mac=(unsigned char*)if_mac.ifr_hwaddr.sa_data;
    //printf("My Hardware Address: %02X:%02X:%02X:%02X:%02X:%02X\n",mac[0],mac[1],mac[2],mac[3],mac[4],mac[5]);

//GET AND PRINT IP ADDRESS
    struct ifreq if_ip;
    memset(&if_ip, 0, sizeof(struct ifreq));
    strncpy(if_ip.ifr_name, "enp0s3", IFNAMSIZ-1);
    if (ioctl(sockfd, SIOCGIFADDR, &if_ip) < 0)
        perror("SIOCGIFADDR");
    struct sockaddr_in *ipAddress = (struct sockaddr_in*)&if_ip.ifr_addr;

    const unsigned char* IP = (unsigned char*)&(ipAddress->sin_addr);
    //printf("My IP Address: %d.%d.%d.%d\n",IP[0], IP[1], IP[2], IP[3]);



//SEND RAW PACKET
    //NOT REALLY NEEDED, left in for completeness
    /* Destination address */
    struct sockaddr_ll socket_address;
    /* Index of the network device */
    socket_address.sll_ifindex = if_idx.ifr_ifindex;
    /* Address length*/
    socket_address.sll_halen = ETH_ALEN;
    /* Destination MAC */
    socket_address.sll_addr[0] = MY_DEST_MAC0;
    socket_address.sll_addr[1] = MY_DEST_MAC1;
    socket_address.sll_addr[2] = MY_DEST_MAC2;
    socket_address.sll_addr[3] = MY_DEST_MAC3;
    socket_address.sll_addr[4] = MY_DEST_MAC4;
    socket_address.sll_addr[5] = MY_DEST_MAC5;
    /* Send packet */
    if (sendto(sockfd, buffer, size, 0, (struct sockaddr*)&socket_address, sizeof(struct sockaddr_ll)) < 0)
    printf("Send failed\n");


}