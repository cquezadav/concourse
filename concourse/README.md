# Concourse
[Concourse](http://cinchapi.org/concourse) is a schemaless and distributed version control database with optimistic availability, serializable transactions and full-text search. Concourse provides a more intuitive approach to data management that is easy to deploy, access and scale with minimal tuning while also maintaining the referential integrity and ACID characteristics of traditional database systems.

## Introduction

### Data Model
The Concourse data model is lightweight and flexible which enables it to support any kind of data at very large scales. Concourse trades unnecessary structural notions of schemas, tables and indexes for a more natural modeling of data based solely on the following concepts:

* **Record** - A logical grouping of data about a single person, place or thing (i.e. an object). Each *record* is a collection of key/value pairs that are together identified by a unique primary key.
*  **Key** - An attribute that maps to a set of *one or more* distinct values. A *record* can have many different keys and the keys in one record do not affect those in another *record*.
*  **Value** - A dynamically typed quanity that is associated with a *key* in a *record*.

#### Data Types
Concourse natively stores most of the Java primitives: boolean, double, float, integer, long, and string (UTF-8). Otherwise, the value of the `#toString()` method for the Object is stored.

#### Links
Concourse supports linking a *key* in one *record* to another *record* using the `#link(String, long, long)` method. Links are one directional, but it is possible to add two inverse links so simulate bi-directionality.

### Transactions
By default, Concourse conducts every operation in  `autocommit` mode where every change is immediately written. Concourse also supports the ability to stage a group of operations in transactions that are atomic, consistent, isolated, and durable using the `#stage()`, `#commit()` and `#abort()` methods.

## Documentation
### Method Summary
* [connect](#connect) - Establish connection and get database handler
* [abort](#abort) - Abort all staged operations and turn on `autocommit` mode
* [add](#add) - Add data that does not exist
* [audit](#audit) - Get a log of revisions
* [clear](#clear) - Clear are values for a key in a record
* [commit](#commit) - Attempt to commit all staged operations and turn on `autocommit` mode
* [describe](#describe) - Describe the keys that exist in a record
* [exit](#exit) - Close the connection
* [fetch](#fetch) - Fetch all the values contained for a key in a record
* [find](#find) - Find records that match a query
* [get](#get) - Get the first contained value for a key in a record
* [getServerVersion](#getServerVersion) - Get the release version of the server
* [link](#link) - Link one record to another
* [ping](#ping) - Check to see if a record exists
* [remove](#remove) - Remove an existing value
* [revert](#revert) - Atomically revert data to a previous state
* [set](#set) - Atomically set a value
* [stage](#stage) - Turn off `autocommit` and stage subsequent operations in a transaction
* [unlink](#unlink) - Remove a link from one record to another
* [verify](#verify) - Verify that a value exists for a key in a record
* [verifyAndSwap](#verifyAndSwap) - Atomically set a new value if the existing value matches

---


### connect
##### `Concourse` connect()
Create a new client connection using the details provided in *concourse_client.prefs*. If the prefs file does not exist or does not contain connection information, then the default connection details (*admin@localhost:1717*) will be used.
###### Returns
the database handler
###### Example
	Concourse concourse = Concourse.connect();

##### `Concourse` connect(String host, int port, String username, String password)
Create a new client connection for *username@host:port* using *password*.
###### Parameters
* host
* port
* username
* password

###### Returns
the database handler
###### Example
	Concourse concourse = Concourse.connect("localhost", 1717, "admin", "admin");
---
### abort
##### `void` abort()
Discard any changes that are currently staged for commit.

After this function returns, Concourse will return to `autocommit` mode and all subsequent changes will be committed immediately.
###### Example
	concourse.stage();
	// make some changes
	concourse.abort();
---
### add
##### `boolean` add(String key, Object value, long record)
Add *key* as *value* to *record* if it is not already contained.
###### Parameters
* key
* value
* record

###### Returns
*true* if *value* is added
###### Example
	concourse.add("foo", "bar", 1);

##### `Map<Long, Boolean>` add(String key, Object value, `Collection<Long>` records)
Add *key* as *value* in each of the *records* if it is not already contained.
###### Parameters
* key
* value
* records

###### Returns
a mapping from each record to a boolean indicating if *value* is added
###### Example
	concourse.add("foo", "bar", concourse.find("foo", Operator.NOT_EQUALS, "bar"));
---
### audit
##### `Map<Timestamp, String>` audit(long record)
Audit *record* and return a log of revisions.
###### Parameters
* record

###### Returns
a mapping from timestamp to a description of a revision
###### Example
	concourse.audit(1);
	
##### `Map<Timestamp, String>` audit(String key, long record)
Audit *key* in *record* and return a log of revisions.
###### Parameters
* key
* record

###### Returns
a mapping from timestamp to a description of a revision
###### Example
	concourse.audit("foo", 1);

---
### clear
#### `void` clear(`Collection<String>` keys, `Collection<Long>` records)
Clear each of the *keys* in each of the *records* by removing every value for each key in each record.
###### Parameters
* keys
* records

###### Example
	concourse.clear(concourse.describe(1), concourse.find("count", Operator.GREATER_THAN", 0));
	
#### `void` clear(`Collection<String>` keys, long record)
Clear each of the *keys* in *record* by removing every value for each key.
###### Parameters
* keys
* record

###### Example
	concourse.clear(concourse.describe(1), 1);
	
#### `void` clear(String key, `Collection<Long>` records)
Clear *key* in each of the *records* by removing every value for key in each record.
###### Parameters
* key
* records

###### Example
	concourse.clear("foo", concourse.search("foo", "bar"));
	
#### `void` clear(String key, long record)
Atomically clear *key* in *record* by removing each contained value.
###### Parameters
* key
* record

###### Example
	concourse.clear("foo", 1);

---
### commit
##### `boolean` commit()
Attempt to permanently commit all the currently staged changes. This function returns *true* if and only if all the changes can be successfully applied. Otherwise, this function returns *false* and all changes are aborted.

After this function returns, Concourse will return to `autocommit` mode and all subsequent changes will be committed immediately.

###### Returns
`true` if all staged changes are successfully committed
###### Example
	concourse.stage();
	
	// make some changes
	
	if(concourse.commit()){
		System.out.println("yay");
	}
	else{
		System.out.println("oops");
	}

---
### describe

---
### exit

---
### fetch

---
### find

---
### get

---
### getServerVersion

---
### link

---
### ping

---
### remove

---
### revert

---
### search

---
### set

---
### stage

---
### unlink

---
### verify

---
### verifyAndSwap

---