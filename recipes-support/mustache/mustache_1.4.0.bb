SUMMARY = "Mustache template system for Go"
DESCRIPTION = "A mustache template parser in Go"
HOMEPAGE = "https://github.com/cbroglie/mustache"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=c85970e6b602135bbaf9ca00d27b7149"

SRC_URI = "https://github.com/cbroglie/mustache/releases/download/v${PV}/mustache_${PV}_linux_amd64.tar.gz"
SRC_URI[sha256sum] = "5f3a9722a071bb9e2aa16d7d575881ff93223e0103059afae6d52c01d15eb96a"

S = "${WORKDIR}"

INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/mustache ${D}${bindir}
}

FILES:${PN} += "${bindir}/mustache"
