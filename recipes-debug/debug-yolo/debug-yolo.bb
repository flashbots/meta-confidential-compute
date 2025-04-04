SUMMARY = "THIS IS ONLY FOR DEBUGGING PURPOSES, DO NOT INCLUDE IN FINAL PRODUCTION."
DESCRIPTION = "This enables SSH ACCESS to allow local devnet setup to setup the image early in the bootstrapping phase"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://debug-yolo"

INITSCRIPT_NAME = "debug-yolo"
INITSCRIPT_PARAMS = "defaults 50"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES:${PN} = "${sysconfdir}/init.d/${INITSCRIPT_NAME}"
