#!/bin/sh
# Start DHCP client and capture the output
OUTPUT=$(udhcpc -i eth0 -n -q 2>&1)

# Extract the DHCP server IP address using awk and remove any trailing comma
DHCP_SERVER=$(echo "$OUTPUT" | awk '/lease of/ {gsub(".*obtained from ","",$0); gsub(",","",$1); print $1}')

echo "The DHCP server IP address is: $DHCP_SERVER"

# Add default gateway to routing table
route add default gw $DHCP_SERVER eth0
route add -host $DHCP_SERVER dev eth0


