DESCRIPTION = "Create a regular file to accumulate system events before System API starts"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://init"

INITSCRIPT_NAME = "system-api-fake-fifo"
INITSCRIPT_PARAMS = "defaults 60"

inherit update-rc.d

do_install() {
    install -m 0755 -d ${D}/var/volatile
    touch ${D}/var/volatile/system-api.fifo
    chmod 666 ${D}/var/volatile/system-api.fifo
}
