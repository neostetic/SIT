## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník - New Cisco</a>
 
## Nastavování Routerů
- `en`
- `conf t`
- `host R0`
- `ban motd "R0"`
- `line con 0`
- `pass cisco`
- `login`
- `exit`
- `ip domain-name franta.local`
- `crypto key generate rsa general-key modulus 4096`
- `line vty 0 15`
- `pass cisco`
- `transport input ssh`
- `login local`
- `user franta secret cisco`
- `enable secret cisco`
- `service password encryption`
- `exit`
- `do wr` / `wr`
- nyní running konfiguraci exportujeme a nacteme na jiný router
#### Router 1
  - `en`
  - `reload`
  - heslo bude `cisco`
  - `host R1`
  - `ban motd "R1"
  - `do wr`
#### Router 1
  - `en`
  - `reload`
  - `host R2`
  - `ban motd "R2"`
  - `do wr`
#### Router 3
  - `en`
  - `reload`
  - `host R3`
  - `ban motd "R3"`
  - `do wr`
### přidání adres IPv6
#### Router 0
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `ipv6 cef`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:9::/64 eui-64`
  - `no sh`
  - `int gi0/1`
  - `ipv6 address 2001:0:0:8::/64 eui-64`
  - `no sh`
  - `int gi0/2`
  - `ipv6 address 2001:0:0:10::/64 eui-64`
  - `no sh`
#### Router 1
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `ipv6 cef`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:9::/64 eui-64`
  - `no sh`
  - ping
    - `do sh ipv6 int br` - pro ukázku adres
    - `ping [adresa]`  
#### Router 1 
  - `en`
  - `conf t` 
  - `ipv5 router ospf 1`
  - `router-id 1.1.1.1`
  - `exit`
  - `int gi0/0`
  - `ipv6 ospf 1 area 0`
  - `en`
  - `conf t`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:5::/64 eui-64`
  - `no sh`
  - `ipv6 ospf 1 area 0`
  - `exit`
  - `ipv6 router ospf 1`
  - `passive-interface gi0/1`
  - `do wr`
#### Router 2
  - `en`
  - `conf t`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:8::/64 eui-64`
  - `no sh`
  - `ipv6 ospf 1 area 0`
  - `exit`
  - `ipv6 router ospf 1`
  - `passive-interface gi0/1`
  - `do wr`
  - `int gi0/2`
  - `ipv6 address 2001:0:0:4::/64 eui-64`
  - `no sh`
  - `int gi0/1`
  - `no sh`
  - `int gi0/1.10`
  - `encapsulation dot1Q 10 native`
  - `ipv6 address 2001:0:0:1::/64 eui-64`
  - `no sh`
  - `exit`
  - `ipv6 unicast-routing`
  - `ipv6 cef`
  - `ipv6 router ospf 1`
  - `router-id 2.2.2.2`
  - `redistribute connected`
  - `passive-interface gi0/2`
  - `passive-interface gi0/1.10`
  - `exit`
  - `int gi0/0`
  - `ipv6 ospf 1 area 0`
#### Router 0
  - `en`
  - `conf t`
  - nastavení OSPF kompletně
    - `ipv6 router ospf 1`
    - `router-id 0.0.0.1`
    - `exit`
    - `int gi0/0`
    - `ipv6 ospf 1 area 0`
    - `int gi0/1`
    - `ipv6 ospf 1 area 0`
    - `int gi0/2`
    - `ipv6 ospf 1 area 0`
    - `do wr`
#### Router 3
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `ipv6 cef`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:10::/64 eui-64`
  - `int gi0/2`
  - `ipv6 address 2001:0:0:2::/64 eui-64`
  - `int gi0/1`
  - `ipv6 address 2001:0:0:11::/64 eui-64`
  - `exit`
  - `router-id 3.3.3.3`
  - `passive-interface gi0/2`
  - `redistribute connected`
  - `redistribute rip ripng metric 1`
  - `exit`
  - `ipv6 router rip ripng`
  - `redistribute ospf 1 metric 1`
  - `exit`
  - `int gi0/0`
  - `ipv6 ospf 1 area 0`
  - `no sh`
  - `int gi0/2` 
  - `ipv6 ospf 1 area 0`
  - `no sh`
  - `int gi0/1`
  - `ipv6 rip ripng enable`
  - `no sh`
  - `do wr`
#### Router 4
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `ipv6 cef`
  - `int gi0/0`
  - `ipv6 address 2001:0:0:11::/64 eui-64`
  - `no sh`
  - `int gi0/1`
  - `ipv6 address 2001:0:0:3::/64 eui-64`
  - `no sh`
  - `int gi0/2`
  - `ipv6 address 2001:0:0:7::/64 eui-64`
  - `no sh`
  - `exit`
  - `ipv6 router rip ripng`
  - `redistribute connected`
  - `exit`
  - `int gi0/0`
  - `ipv6 rip ripng enable`
  - `do wr`
- kontrola - `ipv6 route`
#### Router 2
  - `int g0/1.10`
  - `ipv6 ospf 1 area 0`
  - `int gi0/2`
  - `ipv6 ospf 1 area 0`
  - `do wr`

## Nastavení Switchů
### vytvoření šablony
#### Switch 0
- `en`
- `conf t`
- `host SW0`
- `ban motd "SW0"`
- `line con 0`
- `pass cisco`
- `login`
- `exit`
- `ip domain-name franta.local`
- `username franta secret cisco`
- `crypto key generate rsa general-key modulus 4096`
- `pass cisco`
- `transport input ssh`
- `login local`
- `exit`
- `enable secret cisco`
- `service password-encryption`
- `do wr`
- vyexportíme a naimportujeme na ostatní routery
#### Switch 1  
  - `en`
  - `reload`
  - `en`
  - `conf t`
  - `host SW1`
  - `ban motd "SW1"`
  - `do wr`
#### Switch 2
  - `en`
  - `reload`
  - `en`
  - `conf t`
  - `host SW2`
  - `ban motd "SW2"`
  - `do wr`
#### Switch 3  
  - `en`
  - `reload`
  - `en`
  - `conf t`
  - `host SW3`
  - `ban motd "SW3"`
  - `do wr`
### přidání adres IPv6
#### Switch 2
  - `en`
  - `conf t` 
  - `sdm prefer dual-ipv4-ipv6 default`
  - ...
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `int vlan1`
  - `ipv6 enable`
  - `ipv6 address autoconfig`
  - `no sh`
  - `do wr`
#### Swicth 3
  - `en`
  - `conf t` 
  - `sdm prefer dual-ipv4-ipv6 default`
  - ...
  - `en`
  - `conf t`
  - `ipv6 unicast-routing`
  - `int vlan1`
  - `ipv6 enable`
  - `ipv6 address autoconfig`
  - `no sh`
  - `do wr`
#### Switch 1 *(více vlan)*
  - `en`
  - `conf t` 
  - `sdm prefer dual-ipv4-ipv6 default`
  - ...
  - `en`
  - `conf t`
  - `vlan 10`
  - `name VLAN10`
  - `vlan 20`
  - `name VLAN20`
  - `exit`
  - `int vlan 10`
  - `ipv6 enable`
  - `ipv6 address autoconfig`
  - `no sh`
  - `exit`
  - `int fa0/2`
  - `switchport mode access`
  - `switchport nonegotiate`
  - `switchport access vlan 10`
  - `int fa0/3`
  - `switchport mode access`
  - `switchport nonegotiate`
  - `switchport access vlan 20`
  - `do wr`
  - `int fa0/1`
  - `switchport mode trunk`
  - `switchport trunk allowed vlan none`
  - `switchport trunk allowed vlan 10,20`
  - `do wr`
  - `end`
  - `sh vlan br` - vypíše vlany
  - `show interfaces switchport`
#### Switch 0
  - `en`
  - `conf t`
  - `sdm prefer dual-ipv4-ipv6 default`
  - ...
  - `en`
  - `conf t`
  - `vlan 10`
  - `name VLAN10`
  - `vlan 20`
  - `name VLAN20`
  - `ipv6 unicast-routing`
  - `int vlan10`
  - `ipv6 enable`
  - `ipv6 address autoconfig`
  - `no sh`
  - `int fa0/2`
  - `switchport mode trunk`
  - `switchport trunk allowed vlan none`
  - `switchport trunk allowed vlan 10,20`
  - `int fa0/3`
  - `switchport mode access`
  - `switchport nonegotiate`
  - `switchport access vlan 10`
  - `no sh`
  - `int fa0/4`
  - `switchport mode access`
  - `switchport nonegotiate`
  - `switchport access vlan 20`
  - `no sh`
  - `int fa0/1`
  - `switchport mode trunk`
  - `switchport trunk allowed vlan none`
  - `switchport trunk allowed vlan 10,20`
  - `switchport trunk native vlan 10`
  - `do wr`
- na PC potřeba zapnout autokonfiguraci
- ping v pc - `ping [adresa druheho pocitace]`

### Menší oprava
#### Router 3
- `en`
- `conf t`
- `ipv6 router rpng`
- `redistribude connected`
- `do wr`

### IPv4
#### Router 0
- `en`
- `conf t`
- `int gi0/0`
- `ip address 134.11.25.69 255.255.255.252`
- `int gi0/1`
- `ip address 134.11.25.65 255.255.255.252`
- `int gi0/2`
- `ip address 134.11.25.73 255.255.255.252`
- `exit`
- `router ospf 1`
- `network 134.11.25.68 255.255.255.252 area 0`
- `network 134.11.25.64 255.255.255.252 area 0`
- `network 134.11.25.73 255.255.255.252 area 0`
- `do wr`

#### Router 1
- `en`
- `conf t`
- `int gi0/0`
- `ip address 134.11.25.70 255.255.255.252`
- `int gi0/1`
- `ip address 134.11.16.1 255.255.252.0`
- `exit`
- `router ospf 1`
- `network 134.11.25.68 255.255.255.252 area 0`
- `do wr`

#### Router 2
- `en`
- `conf t`
- `int gi0/0`
- `ip address 134.11.25.66 255.255.255.252`
- `int gi0/1.10`
- `ip address 134.11.0.1 255.255.248.0`
- `int gi0/2`
- `ip address 134.11.22.1 255.255.254.0`
- `exit`
- `ro ospf 1`
- `network 134.11.25.64 255.255.255.252 area 0`
- `network 134.11.0.0 255.255.248.0 area 0`
- `do wr`

#### Router 3
- `en`
- `conf t`
- `int gi0/0`
- `ip address 134.11.25.74 255.255.255.252`
- `int gi0/2`
- `ip address 134.11.20.1 255.255.254.0`
- `int gi0/1`
- `ip address 134.11.25.77 255.255.255.252`
- `exit`
- `ro ospf 1`
- `network 134.11.25.72 255.255.255.252 area 0`
- `network 134.11.20.0 255.255.254.0 area 0`
- `redistribute rip metric 1 subnets`
- `exit`
- `ro rip`
- `ver 2`
- `network 134.11.25.79`
- `redistibute ospf 1 metric 1`
- `do wr`

#### Router 4
- `en`
- `conf t`
- `int gi0/0`
- `ip address 134.11.25.78 255.255.255.252`
- `int gi0/1`
- `ip address 134.11.8.1 255.255.248.0`
- `int gi0/2`
- `ip address 134.11.25.1 255.255.255.192`
- `exit`
- `router rip`
- `ver 2`
- `network 134.11.25.76`

#### Switch 1
- `en`
- `conf t`
- `int vlan10`
- `ip address 134.11.0.2 255.255.248.0`
- `exit`
- `ip default-getaway 134.11.0.1`
- `do wr`

#### Switch 0
- `en`
- `conf t`
- `int vlan10`
- `ip address 134.11.0.3 255.255.248.0`
- `exit`
- `ip defaiult-gateway 134.11.0.1`
- `do wr`

#### Switch 2
- `en`
- `conf t`
- `int vlan1`
- `ip address 134.11.20.2 255.255.254.0`
- `exit`
- `ip default-gateway 134.11.20.1`
- `do wr`

#### Switch 3
- `en`
- `conf t`
- `int vlan1`
- `ip address 134.11.8.2 255.255.248.0`
- `exit`
- `ip default-gateway 134.11.8.1`
- `do wr`

### IPv4 na PC
- PC2 > config > gateway IPv4 : 134.11.0.1 , IP: 134.11.0.6 / 255.255.248.0
- PC0 > config > gateway IPv4 : 134.11.0.1 / IP: 134.11.0.4 / 255.255.248.0
- PC1 > config > gateway IPv4 : 134.11.0.1 / IP: 134.11.0.3 / 255.255.248.0
- PC3 > config > gateway IPv4 : 134.11.0.1 , IP: 134.11.0.7 / 255.255.248.0
- PC4 > config > gateway IPv4 : 134.11.20.1 , IP: 134.11.20.3 / 255.255.248.0
- PC5 > config > gateway IPv4 : 134.11.8.1 , IP: 134.11.8.3 / 255.255.248.0

### Autentizace
#### Router 0
- `en`
- `conf t`
- `int gi0/0`
- `ip ospf message-digest-key 1 md5 cisco`
- `int gi0/1`
- `ip ospf message-digest-key 1 md5 cisco`
- `int gi0/2`
- `ip ospf message-digest-key 1 md5 cisco`
- `exit`
- `router ospf 1`
- `area 0 authetication message-diggest`
- `do wr`

#### Router 1
- `en`
- `conf t`
- `int gi0/0`
- `ip ospf message-digest-key 1 md5 cisco`
- `exit`
- `router ospf 1`
- `area 0 authetication message-diggest`
- `do wr`

#### Router 2
- `en`
- `conf t`
- `int gi0/0`
- `ip ospf message-digest-key 1 md5 cisco`
- `exit`
- `router ospf 1`
- `area 0 authetication message-diggest`
- `do wr`

#### Router 3
- `en`
- `conf t`
- `int gi0/0`
- `ip ospf message-digest-key 1 md5 cisco`
- `exit`
- `router ospf 1`
- `area 0 authetication message-diggest`
- `do wr`

<p align="right">
  <a href="./..">Go Back</a>
</p>
