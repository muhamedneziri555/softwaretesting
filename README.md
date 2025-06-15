# javaPharma

<h2>Pharmacy Management System</h2>

<h4>Overview</h4>
<p>
  This application serves as a Pharmacy Management System, allowing users to manage information about medicines, their details, and inventory.
</p>

<h4>Entities</h4>
<ul>
  <li>
    Medicines - Register and manage information about medicines.
    <ul>
      <li>Name, Created Date, Expiry Date, Category,Manufacturer </li>
    </ul>
  </li>
  <li>
    Category-Managed with different categories
    <ul>
      <li>Name</li>
    </ul>
  </li>

<li>
   Date Created- Managed with created dates of medicines
    <ul>
      <li>Date</li>
    </ul>
  </li>
<li>
   Expiry Date- Managed with Expired  dates of Medicines
    <ul>
      <li>Date</li>
    </ul>
  </li>
<li>
  Manufacturer - Managed with different Manufacturers
    <ul>
      <li>Name</li>
    </ul>
  </li>
</ul>

<h4>Functionality</h4>
<ul>
  <li>CRUD operations on Medicines </li>
  <li>Search Medicines by Name, Created Date, Expiry Date,Category,Manufacturer</li>
 
</ul>

<h4>Getting Started</h4>
<hr>

### Setup

<h4>Requirements</h4>
<h4>Install these software first:</h4>
<ul>
<li>InteliJ IDEA.</li>
<li>Java SE Development Kit.</li>
<li>Insomnia (For testing apis)</li>
<li>XAMPP (Apache server - that contains MariaDB, PHP)</li>
</ul>

<hr>
<h3>Programming Languages used</h3>

![Java](https://img.shields.io/badge/Language-Java-red)
![SQL](https://img.shields.io/badge/Language-SQL-red)

<hr>

### Installations

Once you clone this project from GitHub or download it in zip, then you need to unzip and open it, in your editor IntelliJ IDEA (make sure
you click on TrustProject also after that let IntelliJ IDEA to install dependencies that we need
to open and run.
<hr>

### Technologies and APIs

![Insomnia](https://img.shields.io/badge/Insomnia-2b0d63?style=for-the-badge&logo=insomnia&logoColor=white)   <p>Insomnia is an API platform for building and using APIs. Insomnia simplifies each step of the API lifecycle and streamlines collaboration so you can create better APIs—faster.</p>  <br>  
![XAMPP](https://img.shields.io/badge/Xampp-F37623?style=for-the-badge&logo=xampp&logoColor=white)  <p>XAMPP is an abbreviation for cross-platform, and it allows you to build projects with API's offline, on a local web server on your computer. </p>

<hr>

## Starting the aplication

1.First thing you have to do for running the project is to turn on APACHE SERVER AND MYSQL on XAMPP, then they will take ports that they need.

2.javaproject.sql => this is the file of database, this one you need to import on your database. ('localhost/phpmyadmin').

3.Also you need to check 'application.properties', for example ...
``` java 
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/javapharmadb
spring.datasource.username=root

#Add password as environment variable
#SPRING_DATASOURCE_PASSWORD=<your-password>
#or
#spring.datasource.password=Mysql@123

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

```

### Dependencies

``` java
plugins {
id 'java'
id 'org.springframework.boot' version '3.2.2'
id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
sourceCompatibility = '17'
}

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-web'
runtimeOnly 'com.mysql:mysql-connector-j'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
useJUnitPlatform()
}


```
The getAllCategories method handles HTTP GET requests, returning a list of Category objects
``` java
@GetMapping
public List<Category> getAllCategories() {
return categoryService.getAllCategories();
}
```
Testing API 
``` java
[
	{
		"id": 1,
		"name": "analgesics"
	},
	{
		"id": 2,
		"name": "antibioticss"
	},
	{
		"id": 3,
		"name": "antidepressants"
	},
	{
		"id": 5,
		"name": "antacids"
	}
]
```
This is the function to see Category by id
``` java
   @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

```

This is the function to create Category
``` java
 @PostMapping
    public Category createCategory(@RequestBody CreateCategoryRequest category) {
        return categoryService.saveCategory(category);
    }

```
 Also there are two other function to update and to delete Category.
Now we will continue to the Medicine Controller to see some other functions. This is the function to get all the Medicines that are stored in our database.

``` java

   @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

```
Testing API - getAllMedicines()

``` java
[
	{
		"id": 2,
		"name": "paracetamol",
		"category": {
			"id": 1,
			"name": "analgesics"
		},
		"manufacturer": {
			"id": 1,
			"name": "Roche"
		},
		"dateCreated": {
			"id": 1,
			"date": "2019-04-12"
		},
		"expiryDate": {
			"id": 1,
			"date": "2027-08-26"
		}
	},
	{
		"id": 3,
		"name": "analgin",
		"category": {
			"id": 1,
			"name": "analgesics"
		},
		"manufacturer": {
			"id": 1,
			"name": "Phizer"
		},
		"dateCreated": {
			"id": 1,
			"date": "2017-01-11"
		},
		"expiryDate": {
			"id": 1,
			"date": "2025-06-26"
		}
	}
]

```
This is the function to get Medicines by Id

``` java
  @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }


```
Here are the function for search Medicine, search Manufacturer,Date Created, Expiry Date,also to update Medicine.
``` java

    @GetMapping("/search")
    public List<Medicine> searchMedicineByName(@RequestParam String name) {
        return medicineService.searchByName(name);
    }

    @GetMapping("/searchManufacturer")
    public List<Medicine> searchByManufacturer(@RequestParam String manufacturer){
        return medicineService.searchByManufacturer(manufacturer);
    }
    @GetMapping("/searchDateCreated")
    public List<Medicine> searchByDateCreated(@RequestParam String date){
        return medicineService.searchByDateCreated(date);
    }
    @GetMapping("/searchExpiryDate")
    public List<Medicine> searchByExpiryDate(@RequestParam String expdate){
        return medicineService.searchByExpiryDate(expdate);
    }
    @PostMapping
    public Medicine createMedicine(@RequestBody CreateMedicineRequest medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id, @RequestBody CreateMedicineRequest medicine) {
        var existingMedicine = medicineService.getMedicineById(id);
        existingMedicine.setName(medicine.getName());
        return medicineService.saveMedicine(medicine);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
    }
```
Now we will show some of  the DateCreated Controller functions. Here is the function of all DateCreated Medicines

``` java
    @GetMapping
    public List<DateCreated> getAllDateCreated() {
        return dateCreatedService.getAllDateCreated();
    }

```
Testing API- getAllDateCreated()

``` java
[
	{
		"id": 1,
		"date": "2017-09-11"
	},
	{
		"id": 2,
		"date": "2018-04-21"
	},
	{
		"id": 3,
		"date": "2020-08-26"
	}
]

```
This is the function to get DateCreated by Id

``` java
    @GetMapping("/{id}")
    public DateCreated getDateCreatedById(@PathVariable Long id) {
        return dateCreatedService.getDateCreatedById(id);
    }
```
Also there are 2 functions to create and to delete DateCreated. Now we will show also some functions of ExpiryDate Controller. This is the function to get all expiry Dates

``` java
@GetMapping
    public List<ExpiryDate> getAllExpiryDates() {
        return expiryDateService.getAllExpiryDates();
    }
```
This is the function  to get Expiry date by id

``` java
 @GetMapping("/{id}")
    public ExpiryDate getExpiryDateById(@PathVariable Long id) {
        return expiryDateService.getExpiryDateById(id);
    }
```
Testing API - getExpiryDateById

``` java
{
	"id": 1,
	"date": "2025-09-11"
}
```
Here are some functions in Manufacturer Controller. This is the functions for

``` java
    @GetMapping
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }
```
Testing API - getAllManufacturers()

``` java
[
	{
		"id": 1,
		"name": "Roche"
	},
	{
		"id": 2,
		"name": "Phizer"
	}
		{
		"id": 3,
		"name": "Alkaloid"
	}
]
```
those are other functions to get manufacturers by id also to create and delete Manufacturer

``` java

    @GetMapping("/{id}")
    public Manufacturer getManufacturerById(@PathVariable Long id) {
        return manufacturerService.getManufacturerById(id);
    }

    @PostMapping
    public Manufacturer createManufacturer(@RequestBody CreateManufacturerRequest manufacturer) {
        return manufacturerService.saveManufacturer(manufacturer);
    }


    @DeleteMapping("/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
```
### Made by

Muhamed Neziri

Besim Kamberi
<hr>
