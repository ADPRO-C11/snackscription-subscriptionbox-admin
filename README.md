# Subscription Box Management by Admin
#### Home Link: http://34.124.168.155/subscription-box
 
### Penanggung Jawab :
#### Muhammad Faishal Adly Nelwan (2206030754)

### ALL FEATURES REQUIRE ADMIN TOKEN
##### Link Get All Subscription BOX
http://34.124.168.155/subscription-box/list

##### Link Create Subscription BOX
http://34.124.168.155/subscription-box/create

##### Link Update Subscription BOX
http://34.124.168.155/subscription-box/update

##### Link Delete Subscription BOX
http://34.124.168.155/subscription-box/{id} 

##### Link Find By ID Subscription BOX
http://34.124.168.155/subscription-box/{id}

##### Link Find By Name
http://34.124.168.155/subscription-box/name/{nameURL}

##### Link Find Distinct Names
http://34.124.168.155/subscription-box/name/{nameURL}

##### Link Find Price Lesser Than
http://34.124.168.155/subscription-box/price/lesser-than/{price}

##### Link Find Price Greater Than
http://34.124.168.155/subscription-box/price/greater-than/{price}

##### Link Find Price Equals
http://34.124.168.155/subscription-box/price/equals/{price}

##### Link Get Admin Subscription Box Logs
http://34.124.168.155/subscription-box/logs


# Monitoring And Profiling
### Grafana
![grafana 1.jpg](images%2Fgrafana%201.jpg)
![grafana 2.jpg](images%2Fgrafana%202.jpg)
![grafana 3.jpg](images%2Fgrafana%203.jpg)
![grafana 4.jpg](images%2Fgrafana%204.jpg)
![grafana 5.jpg](images%2Fgrafana%205.jpg)
![grafana 6.jpg](images%2Fgrafana%206.jpg)

### Profiling
![profiling 1.jpg](images%2Fprofiling%201.jpg)

Dapat dilihat dari profiling diatas bahwa method yang paling mahal adalah findAll() subscriptionbox dan update() subscriptionbox.  
Akan tetapi, saya belum menemukan implementasi baru yang dapat mengalahkan waktu tersebut. Sehingga tidak dilakukan refactoring apa-apa terhadap kedua metode ini.

### Refactoring
![refactor 1.jpg](images%2Frefactor%201.jpg)
![refactor 1.2.jpg](images%2Frefactor%201.2.jpg)

Pada save method saya (untuk creation subscriptionBox) terdapat kode yang tidak menerapi SOLID principle.
Sehingga saya melakukan refactoring agar logic dari metode tersebut dipisah-pisah menjadi function tersendiri untuk menerapi single-responsibility principle.

![refactor 2.0.jpg](images%2Frefactor%202.0.jpg)