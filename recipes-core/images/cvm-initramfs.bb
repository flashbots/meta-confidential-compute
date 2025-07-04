DESCRIPTION = "Tiny image capable of booting a device with systemd init"

CVM_DEPS = "init-ifupdown netbase busybox"

# Add systemd and its minimal dependencies
SYSTEMD_DEPS = "systemd \
    systemd-extra-utils \
    udev \
    dhcpcd\
    systemd-boot\
"

PACKAGE_INSTALL = "ca-certificates \
    date-sync \
    logrotate \
    cronie \
    azure-complete-provisioning \
    dropbear \
    ${CVM_DEPS} \
    ${SYSTEMD_DEPS} \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${ROOTFS_BOOTSTRAP_INSTALL} \
"

INITRAMFS_MAXSIZE = "20000000"

# Do not pollute the initrd image with rootfs features
# IMAGE_FEATURES = ""

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
