DESCRIPTION = "Create a regular file to accumulate system events before System API starts"
LICENSE = "CLOSED"

do_install() {
    install -m 1777 -d ${D}/var/volatile
    touch ${D}/var/volatile/system-api.fifo
    chmod 666 ${D}/var/volatile/system-api.fifo
}

FILES:${PN} = "/var/volatile/system-api.fifo"
