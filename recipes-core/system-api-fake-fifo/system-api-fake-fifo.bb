SUMMARY = "System API fake FIFO"
DESCRIPTION = "Create a regular file to accumulate system events before System API starts"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://init"

INITSCRIPT_NAME = "system-api-fake-fifo"
INITSCRIPT_PARAMS = "defaults 60"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES:${PN} = "${sysconfdir}/init.d/${INITSCRIPT_NAME}"
