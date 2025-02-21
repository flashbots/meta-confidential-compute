DESCRIPTION = "Syncs the date and time of the VM periodically using BusyBox ntpd"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://init"

INITSCRIPT_NAME = "date-sync"
INITSCRIPT_PARAMS = "defaults 80"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/date-sync
}
