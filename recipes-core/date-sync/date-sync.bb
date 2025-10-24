DESCRIPTION = "Syncs the date and time of the VM periodically using BusyBox ntpd"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://date-sync.service"

# Replace update-rc.d with systemd
inherit systemd

do_install() {
    # Install systemd service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/date-sync.service ${D}${systemd_system_unitdir}/
    
    # Replace the interval in the service file
    sed -i 's/SYNC_INTERVAL=60/SYNC_INTERVAL=${TIME_SYNC_INTERVAL}/' ${D}${systemd_system_unitdir}/date-sync.service
}

# Enable the service by default
SYSTEMD_SERVICE:${PN} = "date-sync.service"
SYSTEMD_AUTO_ENABLE = "enable"

# Add runtime dependency on systemd
RDEPENDS:${PN} += "systemd"

FILES:${PN} += "${systemd_system_unitdir}/date-sync.service"
