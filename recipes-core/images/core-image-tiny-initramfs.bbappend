PACKAGE_INSTALL = "${DISTRO_EXTRA_RDEPENDS} packagegroup-core-boot ${VIRTUAL-RUNTIME_base-utils} ${VIRTUAL-RUNTIME_dev_manager} base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

IMAGE_FEATURES = "${EXTRA_IMAGE_FEATURES}"

python tinyinitrd () {
}
