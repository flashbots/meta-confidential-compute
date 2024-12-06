# Simple initramfs image artifact generation for tiny images.
DESCRIPTION = "Tiny image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently. core-image-tiny-initramfs doesn't \
actually generate an image but rather generates boot and rootfs artifacts \
that can subsequently be picked up by external image generation tools such as wic."

CVM_DEPS = "busybox-mdev init-ifupdown initscripts base-files base-passwd netbase busybox-udhcpd"

PACKAGE_INSTALL = "ca-certificates sysvinit busybox-udhcpd date-sync logrotate cronie ${CVM_DEPS} ${VIRTUAL-RUNTIME_base-utils} ${ROOTFS_BOOTSTRAP_INSTALL}"

INITRAMFS_MAXSIZE = "20000000"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""
python () {
    # Check if DEBUG_TWEAKS_ENABLED is set in the environment or in local.conf
    debug_tweaks_enabled = d.getVar('DEBUG_TWEAKS_ENABLED')

    if debug_tweaks_enabled is None:
        # If not set, check the original environment
        origenv = d.getVar("BB_ORIGENV", False)
        if origenv:
            debug_tweaks_enabled = origenv.getVar('DEBUG_TWEAKS_ENABLED')

    if debug_tweaks_enabled:
        # If DEBUG_TWEAKS_ENABLED is set (to any non-empty value), keep its value
        d.setVar('DEBUG_TWEAKS_ENABLED', debug_tweaks_enabled)
    else:
        # If DEBUG_TWEAKS_ENABLED is not set, set it to '1' by default
        d.setVar('DEBUG_TWEAKS_ENABLED', '0')

    # set the image features based on the value of DEBUG_TWEAKS_ENABLED
    if d.getVar('DEBUG_TWEAKS_ENABLED') == '1':
        # give a warning that the debug tweaks are enabled
        bb.warn("Debug tweaks are enabled in the image")
        # add the debug-tweaks feature to the image if DEBUG_TWEAKS_ENABLED is set
        d.appendVar('IMAGE_FEATURES', ' debug-tweaks')
        # add dropbear to the package install list to be able to login for debugging purposes
        d.appendVar('PACKAGE_INSTALL', ' dropbear')
}

export IMAGE_BASENAME = "cvm-initramfs"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

# don't actually generate an image, just the artifacts needed for one
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Use the same restriction as initramfs-live-install
COMPATIBLE_HOST = "x86_64.*-linux"

# QB_KERNEL_CMDLINE_APPEND += "debugshell=3 init=/bin/busybox sh init"
