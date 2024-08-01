FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI += "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'podman', \
                         'file://podman.scc file://podman.cfg', '', d)} \
"
