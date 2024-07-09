DESCRIPTION = "syncs the date and time of the VM"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init"

INITSCRIPT_NAME = "date-sync"
INITSCRIPT_PARAMS = "defaults 96"

inherit update-rc.d
# This is a hack solution till we figure out a way to do this with NTP or chrony inside of yocto

do_install() {
	install -d ${D}${sysconfdir}/init.d
        cp ${WORKDIR}/init ${D}${sysconfdir}/init.d/date-sync
        chmod 755 ${D}${sysconfdir}/init.d/date-sync
}
