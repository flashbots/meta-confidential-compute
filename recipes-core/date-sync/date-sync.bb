DESCRIPTION = "Syncs the date and time of the VM periodically using BusyBox ntpd"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://init"

INITSCRIPT_NAME = "date-sync"
INITSCRIPT_PARAMS = "defaults 96"

inherit update-rc.d

python () {
    # Check if TIME_SYNC_INTERVAL is set in the BitBake environment or in local.conf
    sync_interval = d.getVar('TIME_SYNC_INTERVAL')
    
    if sync_interval is None:
        # If not set in BitBake environment, check the original environment
        origenv = d.getVar("BB_ORIGENV", False)
        if origenv:
            sync_interval = origenv.getVar('TIME_SYNC_INTERVAL')
    
    if sync_interval:
        # If TIME_SYNC_INTERVAL is set (to any non-empty value), keep its value
        d.setVar('TIME_SYNC_INTERVAL', sync_interval)
    else:
        # If TIME_SYNC_INTERVAL is not set, set it to '600' seconds (10 mins) by default
        d.setVar('TIME_SYNC_INTERVAL', '600')

    bb.note("Time sync interval set to: %s seconds" % d.getVar('TIME_SYNC_INTERVAL'))
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/date-sync
    sed -i 's/SYNC_INTERVAL=60/SYNC_INTERVAL=${TIME_SYNC_INTERVAL}/' ${D}${sysconfdir}/init.d/date-sync
}
