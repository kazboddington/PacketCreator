#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux
CND_DLIB_EXT=so
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/_ext/6f293d52/RawPacketSender.o


# C Compiler Flags
CFLAGS=-shared

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk dist/libRawPacketOutputterJNI.so

dist/libRawPacketOutputterJNI.so: ${OBJECTFILES}
	${MKDIR} -p dist
	${LINK.c} -o dist/libRawPacketOutputterJNI.so ${OBJECTFILES} ${LDLIBSOPTIONS} -shared -fPIC

${OBJECTDIR}/_ext/6f293d52/RawPacketSender.o: /home/zak/NetBeansProjects/RawPacketOutputterJNI/RawPacketSender.c 
	${MKDIR} -p ${OBJECTDIR}/_ext/6f293d52
	${RM} "$@.d"
	$(COMPILE.c) -g -I/usr/local/java/jdk1.8.0_101/include -I/usr/local/java/jdk1.8.0_101/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/6f293d52/RawPacketSender.o /home/zak/NetBeansProjects/RawPacketOutputterJNI/RawPacketSender.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} dist/libRawPacketOutputterJNI.so

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
