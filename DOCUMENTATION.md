# SynergyKit Android SDK
<p align="left" style="margin-bottom:0;" >
<img src="https://synergykit.blob.core.windows.net/synergykit/synergykitlogo.png" alt="Synergykit" title="Synergykit" width="224px">
</p>

Letsgood.com runs Backend as a Service SynergyKit for **fast and simple mobile/web/desktop applications development**. SynergyKit allows enterpreneurs implement an idea to project fast and at low cost like Lean Startup.

We know how hard can be to work with untried API, so we prepared SDKs for mostly used platforms.

**Another SDKs**

- [iOS SDK](https://github.com/SynergyKit/synergykit-sdk-ios)
- [Node.js SDK](https://github.com/SynergyKit/synergykit-sdk-nodejs)
<br>

**Table of content**

[TOC]

## Sample Application
Almost all possibilities of SynergyKit are presented in Sample Application that was developed next to SDK as introduction of how it works.

### Sample App Installation 

 1. Clone or download the repository.
`git clone https://github.com/SynergyKit/synergykit-sdk-android`
 2. Open **Android Studio**.
 3. Open project `SynergyKit SDK Android` from downloaded repository.
 4. Run Gradle task `installLocalSdkDebug` in  `sample-app` module.

## Installation

SynergyKit SDK Android is available through [Maven](https://bintray.com/letsgood/maven/synergykit-sdk-android/view) repository. **Minimum Android SDK version is 14**.

To install it, simply add the following line to your build.gradle files:

```java 
compile 'com.letsgood:synergykit-sdk-android:2.1.4'
```



## Architecture 


### Building model
SynergyKit has two base objects `SynergykitObject` and `SynergykitUser`.  Every object which you want to store in SynergyKit must extends `SynergykitObject`. Every user you want to sign in, sign out or store in SynergyKit must extends `SynergykitUser`.   

SDK uses [Google Gson](https://code.google.com/p/google-gson/) to serialize object to JSON and deserialize object from JSON.  Every argument of your objects which would be serialize/deserialize must have `@Expose` annotation. 

```java
public class DemoObject extends SynergykitObject {

    /* Attributes */
    @Expose
    private String text; //this attribute will be serialize/deserialize

    /* Text getter */
    public String getText() {
        return text;
    }

    /* Text setter */
    public void setText(String text) {
        this.text = text;
    }

}
```

```java
public class DemoUser extends SynergykitUser {

    /* Attributes */
    @Expose
    private String name; //this attribute will be serialize/deserialize
    private int age; //this attribute will not be serialize/deserialize

    /* Name getter */
    public String getName() {
        return name;
    }

    /* Name setter */
    public void setName(String name) {
        this.name = name;
    }

    /* Age getter */
    public int getAge() {
        return age;
    }

    /* Age setter */
    public void setAge(int age) {
        this.age = age;
    }
}
```

## SynergyKit Initialization
Before you can start developing with SynergyKit SDK you need to set tenant and key. Is recommended to set it up in `onCreate` method of your application class (class extended Application).

You can find it in **Settings > Application keys > Tenant** and **Settings > Application keys > Value** in SynergyKit web application.

```java
Synergykit.init(APPLICATION_TENANT, APPLICATION_KEY);
```

## Responses handling
There are many options that you can receive at the end of API communication. SDK provides many listeners to hadle responses.

Every request has response listener which provides `doneCallback` and `errorCallback`. `doneCallback` is called when everything was done without error. `errorCallback` otherwise.

For example base `ResponseListener` 

```java
public interface ResponseListener {
	public void doneCallback(int statusCode, SynergykitObject object);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
```
## Documents
Documents are data saved in collections. Collections are basically tables in database where you can store your data. By sending requests to the documents endpoint, you can list, create, update or delete documents.

### Create new document

| Parameter | Type | Notes ||
|:-|:-|:-|:-:|
|collection |String| Location of document | **required**
|object |SynergykitObject| SynergykitObject or object extended SynergykitObject |**required**
|listener |ResponseListener||  optional
|parallelMode| boolean | Indicates whether the requests are provided in parallel or in series|  **required**

```java
Synergykit.createRecord("collection",new SynergykitObject(),new ResponseListener() {
   @Override
   public void doneCallback(int statusCode, SynergykitObject object) {
		//your code
   }

   @Override
   public void errorCallback(int statusCode, SynergykitError errorObject) {
		//your code
   }
},true);
```
### Retrieve an existing document by ID

| Parameter | Type | Notes ||
|:-|:-|:-|:-:|
|collection |String| Location of document | **required**
|recordId |String| Record identificator |**required**
|type |Type| Object type |**required**
|listener |ResponseListener||  optional
|parallelMode| boolean | Indicates whether the requests are provided in parallel or in series|  **required**

```java
Synergykit.getRecord("collection","recordId",SynergykitObject.class,new ResponseListener() {
  @Override
  public void doneCallback(int statusCode, SynergykitObject object) {
    //your code
  }

  @Override
  public void errorCallback(int statusCode, SynergykitError errorObject) {
	//your code
  }
},false);
```
### Update document
| Parameter | Type | Notes ||
|:-|:-|:-|:-:|
|collection |String| Location of document | **required**
|object |SynergykitObject|SynergykitObject or object extended SynergykitObject  |**required**
|type |Type| Object type |**required**
|listener |ResponseListener||  optional
|parallelMode| boolean | Indicates whether the requests are provided in parallel or in series|  **required**

```java
 Synergykit.updateRecord("collection",object,new ResponseListener() {
     @Override
     public void doneCallback(int statusCode, SynergykitObject object) {
		//your code
     }

     @Override
     public void errorCallback(int statusCode, SynergykitError errorObject) {
		//your code
     }
 },true);
```
### Delete document

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|collection |String| Location of document | **required**
|recordId |String| Record identificator |**required**
|listener |DeleteResponseListener||  optional
|parallelMode| boolean | Indicates whether the requests are provided in parallel or in series|  **required**

```java
Synergykit.deleteRecord("collection","recordId",new DeleteResponseListener() {
    @Override
    public void doneCallback(int statusCode) {
		//your code
    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {
		//your code
    }
},false);
```

## Real-time data observerving
SDK supports real time communication through sockets. With this funcion you can develop dynamic applications.

### Connect
To connect socket to SynergyKit server you must call SynergyKit.connect() method.

```java
SynergyKit.connectSocket();
```

### Disconnect
To disconnect socket use method disconnectSocket();
```java
SynergyKit.disconnectSocket();
```

### Checking connection state
If you need to check states `connected`, `disconnected` and `reconnected` you can call connect method with listener.

```java
SynergyKit.connectSocket(new SocketStateListener() {
   @Override
   public void connected() {
      //this method is called when socket was connected
   }

   @Override
   public void disconnected() {
	 //this method is called when socket was disconnected
   }

   @Override
   public void reconnected() {
	 //this method is called when socket was reconnected
   }
});
```
### Start observing whole collection

SynergyKit provides listening changes of records in data collection. You can listen  `created`, `updated`, `patched`, `deleted` states.  You can set / unset listener(s) befor or after socket is connected (Both situation are supported).

```java

/*
* If you wanna listen new posted records to collection demo_collection you can use this code.
*/

private static final String COLLECTION = "demo_collection";

.
.
.

SynergyKit.onSocket(Socket.MESSAGE_CREATED,COLLECTION,new SocketEventListener() {
	  @Override
      public void call(Object... args) {
		// this method is called when new record was created in collection demo_collection
             
      }

      @Override
      public void subscribed() {
		// this method is called when listener was subscribed and is ready to listen
      }

      @Override
      public void unsubscribed() {
		// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed 
      }

      @Override
      public void unauthorized() {
		// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.
      }
     });
```
### Start observing collection with filter

SynergyKit provides listening changes of filtered collection records. Just create your filter and set socket listener.

```java

/*
* If you wanna listen new posted records from user called TestUser on collection demo_collection you can use this code.
*/

private static final String COLLECTION = "demo_collection";
private static final String FILTER_NAME = "filter_records_from_testuser";

.
.
.

//Build your OData Filter
ODataBuilder oDataBuilder = ODataBuilder.newInstance().setFilter(Filter.buildAttribute("from"),Filter.OPERATOR_EQUAL,Filter.buildParametr("TestUser"));

//Create filter
SynergyKitSocketFilter filter = new SynergyKitSocketFilter(FILTER_NAME,oDataBuilder.build());

//Set listener with filter
SynergyKit.onSocket(Socket.MESSAGE_CREATED,COLLECTION, filter,new SocketEventListener() {
	  @Override
      public void call(Object... args) {
		// this method is called when new record was created in collection demo_collection
             
      }

      @Override
      public void subscribed() {
		// this method is called when listener was subscribed and is ready to listen
      }

      @Override
      public void unsubscribed() {
		// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed 
      }

      @Override
      public void unauthorized() {
		// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.
      }
     });
```
### Stop observing
If you don't want to listen collection changes anymore you can call SynergyKit.offSocket(...) method.
```java

/*
* If you wanna stop listen new posted records to collection demo_collection you can use this code.
*/


private static final String COLLECTION = "demo_collection";

.
.
.

SynergyKit.offSocket(Socket.MESSAGE_CREATED,COLLECTION);
```
### Speak communication
Communication without data storage from device to device.

#### Send speak

```java

/*
* For example if you're developing chat and you wanna send message about typing you can use this code.
*/

private static final String EVENT_TYPING = "typing";

.
.
.

Message message = new Message();
message.setText("TestUser is typing");

SynergyKit.emitViaSocket(EVENT_TYPING,message);
```
#### Receive speak

```java

/*
* For example if you're developing chat and you wanna listen state typing you can use this code.
*/

private static final String EVENT_TYPING = "typing";

.
.
.

SynergyKit.onSocket(EVENT_TYPING,new SocketEventListener() {
	  @Override
      public void call(Object... args) {
		// this method is called when someone is typing - someone send emit typing.
        
        String data =((JSONObject) args[0]).toString();
        final Message message = GsonWrapper.getGson().fromJson(data, Message.class);     
        
      }

      @Override
      public void subscribed() {
		// this method is called when listener was subscribed and is ready to listen
      }

      @Override
      public void unsubscribed() {
		// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed 
      }

      @Override
      public void unauthorized() {
		// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.
      }
     });
```

## Queries
You can retrieve multiple objects at once by sending a request with query. If query has no conditions API returns simply list of all objects in collection.

For more complex filtering and sorting SynergyKit accepts OData standard. These queries can be used with data, users and files.

### Available conditions
Query string is builded according to [OData Protocol](http://odata.org) and is appended to the end of the url.

The OData Protocol specification defines how to standardize a typed, resource-oriented CRUD interface for manipulating data sources by providing collections of entries which must have required elements.

#### filter
Equivalent to if (field == "value" && secondField >= 33 || thirdField < 132000).
```java
uriBuilder.setFilter(Filter.newInstance()
                 .setFilter(Filter.buildAttribute("field")
				 + Filter.buildParametr("value")
                 + Filter.OPERATOR_AND 
                 + Filter.buildAttribute("secondField") 
                 + Filter.OPERATOR_GREATER_THAN_OR_EQUAL 
                 + Filter.buildParametr(33)
                 + Filter.OPERATOR_OR
                 + Filter.buildAttribute("thirdField") 
                 + Filter.OPERATOR_LESS_THAN 
                 + Filter.buildParametr(132000)));
```
Available relation operators

- `==` or `eq`
- `!=` or `ne`
- `>=` or `ge`
- `<=` or `le`
- `>` or `gt`
- `<` or `lt`

#### startswith
```java
uriBuilder.setFilter(Filter
			.newInstance()
			.setFilter(Filter.buildStartsWithFilter("name","a")));
```
#### endswith
```java
uriBuilder.setFilter(Filter
			.newInstance()
			.setFilter(Filter.buildEndsWithFilter("name","z")));
```
#### substringof
```java
uriBuilder.setFilter(Filter
			.newInstance()
			.setFilter(Filter.buildSubStringOfFilter("name","bc")));
```
#### in
```java
 String[] names = new String[2];
        names[0] = "Lucas";
        names[1] = "Thomas";
        
uriBuilder.setFilter(Filter
			.newInstance()
			.setFilter("name",Filter.OPERATOR_IN,Filter.buildArrayParameter(names)));
```
#### nin
```java
String[] names = new String[2];
        names[0] = "John";
        names[1] = "Mark";
        
uriBuilder.setFilter(Filter.newInstance()			.setFilter("name",Filter.OPERATOR_NOT_IN,Filter.buildArrayParameter(names)));
```
#### select
```java
uriBuilder.addSelect("firstName")
             .addSelect("lastName");
```
#### top
```java
uriBuilder.setTop(5);
```
#### orderby
```java
uriBuilder.setOrderByAsc("name");
```
#### inlinecount
```java
 uriBuilder.setEnabled(true);
```
#### skip
```java
uriBuilder.setSkip(32);
```

### Querying objects
If query is prepared, you just call some of SynergyKit methods.
```java
SynergykitConfig config = SynergykitConfig
						 .newInstance()
	                     .setUri(uriBuilder.build())
	                     .setParallelMode(false)
	                     .setType(SynergykitObject[].class);

Synergykit.getRecords(config,new RecordsResponseListener() {
 @Override
 public void doneCallback(int statusCode, SynergykitObject[] objects) {
     
 }

 @Override
 public void errorCallback(int statusCode, SynergykitError errorObject) {

 }
});
```

### List all users

`SQuery` with `SynergykitUser` object without conditions.
```java
SynergykitUri synergykitUri = UriBuilder
                                 .newInstance()
                                 .setResource(Resource.RESOURCE_USERS)
                                 .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(false)
                                .setType(SynergykitUser[].class)
                                .setUri(synergykitUri);

Synergykit.getUsers(config,new UsersResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser[] users) {
        
    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
});
```
### List all documents
```java
SynergykitUri synergykitUri = UriBuilder
                                 .newInstance()
                                 .setResource(Resource.RESOURCE_DATA)
                                 .setCollection("collection")
                                 .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(false)
                                .setType(SynergykitObject[].class)
                                .setUri(synergykitUri);

Synergykit.getRecords(config,new RecordsResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitObject[] users) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
});
```
### List all files
```java
SynergykitUri synergykitUri = UriBuilder
                                      .newInstance()
                                      .setResource(Resource.RESOURCE_FILES)
                                      .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(false)
                                .setType(SynergykitFile[].class)
                                .setUri(synergykitUri);

Synergykit.getFiles(config, new FilesResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitFile[] users) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
});
```

## Users
Users are alfa and omega of every application. In SynergyKit you can easily work with your users by methods listed below.
### Create a new user	

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser or object extended SynergykitUser | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.createUser(new SynergykitUser(),new UserResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser user) {
		//your code
    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {
		//your code
    }
},false);
```
### Retrieve an existing user by ID

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|userId |String| User identification | **required**
|type |Type| User object type | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.getUser("userId",SynergykitUser.class,new UserResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser user) {
		//your code
    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {
		//your code
    }
},false);
```
### Update user
Save method executes `PUT` request if `_id` is set. 

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser or object extended SynergykitUser | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.updateUser(new SynergykitUser(),new UserResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser user) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```
### Delete user

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser or object extended SynergykitUser to delete | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.deleteUser(user,new DeleteResponseListener() {
    @Override
    public void doneCallback(int statusCode) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```

### Add role

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser or object extended SynergykitUser| **required**
|role |String| Role define in SynergyKit | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.addRole(user,"role",new UserResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser user) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```
### Remove role

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser or object extended SynergykitUser| **required**
|role |String| Role define in SynergyKit | **required**
|listener |UserResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.removeRole(user,"role",new UserResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitUser user) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```

### Add platform to user
Platforms are useful for pairing individual mobile devices or web applications to the user via registration ID. After assignment platform to the user you will be able to send push notifications to the device or application.

**Before you can work with platforms** of user is needed to login first. After successful login SDK receives sessionToken for authentication of user. Token is held by the SDK and is automatically inserted into the Headers.

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|platform |SynergykitPlatform|  | **required**
|listener |PlatformResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.addPlatform(platform,new PlatformResponseListener() {
        @Override
        public void doneCallback(int statusCode, SynergykitPlatform platform) {

        }

        @Override
        public void errorCallback(int statusCode, SynergykitError errorObject) {

        }
    },true);
```

### Retrive platform 

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|platformId |String|  | **required**
|listener |PlatformResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.getPlatform(platformId",new PlatformResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitPlatform platform) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```

### Update platform
Platforms contain of a few parameters but only two are updatable. Save method executes `PUT` request if `_id` is set, it could change `development` and `registrationId`. 

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|platform |SynergykitPlatform|  | **required**
|listener |PlatformResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.updatePlatform(platform,new PlatformResponseListener() {
   @Override
   public void doneCallback(int statusCode, SynergykitPlatform platform) {

   }

   @Override
   public void errorCallback(int statusCode, SynergykitError errorObject) {

   }
},true);
```
### Delete platform

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|platformId |String|  | **required**
|listener |DeleteResponseListener|  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
Synergykit.deletePlatform(platformId,new DeleteResponseListener() {
      @Override
      public void doneCallback(int statusCode) {

      }

      @Override
      public void errorCallback(int statusCode, SynergykitError errorObject) {

      }
  },true);
```

### Activating user
By default, user is not activated. This mean, that you can use this state to validate user e-mail address by sending him activation link.

To activate user, send an email with this activation link /v2.1/users/activation/[ACTIVATION_HASH]. You can provide parameter callback with url address where you want to redirect user after activation.

Or **if you know that e-mai address is valid** you can activate user with SDK.

```java
user.setActivated(true);
```
### Login user
If user was registrated via normal way, which means by email and password, you can authenticate him with login method.

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|user |SynergykitUser| SynergykitUser with email and password | **required**
|listener | UserResponseListener |  | optional

```java
Synergykit.loginUser(user,new UserResponseListener() {
      @Override
      public void doneCallback(int statusCode, SynergykitUser user) {
          
      }

      @Override
      public void errorCallback(int statusCode, SynergykitError errorObject) {

      }
  });
```

## Communication
In SynergyKit you can communicate with your users by different ways. There are listed some methods below this section.

One way is sending push notifications into user devices. This action need to have filled your API key for Android devices in Settings, section Android. For push notifications into iOS devices you need to fill your password and certificates into Apple section in Settings.

Another way is sending emails to your users. For this you need to create email templates in administration under Mailing section.

### Send notification

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|notification| NSArray| List of recipient | **required**
|listener | NotificationResponseListener |  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
SynergykitNotification notification = SynergykitNotification
                                        .newInstance()
                                        .setAlert("My notification")
                                        .addUserId("userId")
                                        .setPayload("payload");

Synergykit.sendNotification(notification,new NotificationResponseListener() {
    @Override
    public void doneCallback(int statusCode) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```
### Send e-mail

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|templateName | String| Name of e-mail template from SynergyKit | **required**
|email |SynergykitEmail| E-mail information | **required**
|listener | EmailResponseListener |  | optional
|parallelMode |boolean|Indicates whether the requests are provided in parallel or in series  | **required**

```java
SynergykitEmail email = SynergykitEmail
                               .newInstace()
                               .setEmail("name@domain.com")
                               .setSubject("subject")
                               .setFrom("your-email@domain.com");

   Synergykit.sendEmail("templateName",email,new EmailResponseListener() {
       @Override
       public void doneCallback(int statusCode) {

       }

       @Override
       public void errorCallback(int statusCode, SynergykitError errorObject) {

       }
   },false);
```
E-mail template should looks like this example.
```
<p>Hello %name%,</p>
<br>
<p>this e-mail was send from SynergyKit Sample Application.</p>
<br>
<p>SynergyKit Team</p>
```

## Files
SynergyKit can be also used for storing as much quantity of files as you need for your application.
### Upload file
SynergyKit Android SDK supports upload bitmaps and byte array. If file is successfully uploaded `SynergykitFile` representing just created file object is returned. `SynergykitFile` contains path to file from where is file accessible.

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|bitmap |Bitmap| Bitmap to upload | **required** |
|listener| FileResponseListener | |	optional |

```java
/*
* Example of bitmap uploading
*/

Synergykit.createFile(bitmap,new FileResponseListener() {
   @Override
   public void doneCallback(int statusCode, SynergykitFile file) {
       
   }

   @Override
   public void errorCallback(int statusCode, SynergykitError errorObject) {

   }
});
```
### Retrieve file by ID

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|fileId |String| File identification | **required**
|listener	|	FileResponseListener	|		|	optional	

```java
Synergykit.getFile("fileId",new FileResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitFile file) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},false);
```
### Delete file

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|fileId |String| File identification | **required**
|listener	|	DeleteResponseListener	|		|	optional	

```java
Synergykit.deleteFile("fileId",new DeleteResponseListener() {
    @Override
    public void doneCallback(int statusCode) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```

## Cloud Code
Our vision is to let developers build any app without dealing with servers. For complex apps, sometimes you just need a bit of logic that isn't running on a mobile device. Cloud Code makes this possible.

Cloud Code runs in the Node.js jailed sandbox and uses strict JavaScript language with some prepared modules and variables, which you can use for your development.


### Run cloud code

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|	cloudCode	|	SynergykitCloudCode	|	SynergykitCloudCode or object extended SynergykitCloudCode with other parameters	|	**required**	|
|	type	|	Type	|	Return object type (Must extend SynergykitObject)	|	**required**	|
|	listener	|	ResponseListener()	|		|	optional	|

```java
 SynergykitCloudCode cloudCode = SynergykitCloudCode.newInstance("cloudCodeName");

 Synergykit.invokeCloudCode(cloudCode, SynergykitObject.class,new ResponseListener() {
     @Override
     public void doneCallback(int statusCode, SynergykitObject object) {

     }

     @Override
     public void errorCallback(int statusCode, SynergykitError errorObject) {

     }
 },true);
```

Example cloud code function should looks like this.
```
callback("Hello " + parameters.name + "!")
```
## Batch request
We know that internet connection is sometimes unstable and we know it's not really good for synchronization algorithm where dozens of requests need to be executed without mistake. Batch request minimizes risk with connection failure - it's all in one or nothing, not first five request, then two failed (walk under the bridge) and at the end three successful.

### BatchItem
You can batch every request you can imagine with `SynergykitBatchItem` object. At first create batch item that says where and how to do it.

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|	method	|	String	|	REST method	|	**required**	|
|	endpoint	|	SynergykitEndpoint	|	REST API endpoint	|	**required**	|
|	body	|	Child of SynergykitObject	|	POST request body	|	optional	|

```java
SynergykitEndpoint endpoint = UriBuilder.newInstance()
                                        .setResource(Resource.RESOURCE_DATA)
                                        .setRecordId("resourceId")
                                        .buildEndpoint();

SynergykitBatchItem batchItem = new SynergykitBatchItem(Synergykit.GET,endpoint);
```
### Adding to batch
Every batch item need to be add to batch which you can send. At firt you must initialize batch. Then you can add BatchItems and send them all together.
```java
Synergykit.initBatch("batchId");

Synergykit.getBatch("batchId").add(batchItem-0);
Synergykit.getBatch("batchId").add(batchItem-1);
.
.
.
Synergykit.getBatch("batchId").add(batchItem-n);
```
### Sending batch
Batch executes every request in the order in which they were added.

| Parameter | Type | Notes | |
|:-|:-|:-|:-:|
|	batchId	|	String	|	Batch identification	|	**required**	|
|	listener	|	BatchResponseListener	|		|	optional	|
|	parallelMode	|	boolean	|		|	optional	|

```java
Synergykit.sendBatch("batchId",new BatchResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitBatchResponse[] batchResponse) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);
```
## Cache
The SynergyKit Android SDK provides Http response cache (HttpResponseCache). Http response cache caches all of your application's HTTP requests. This cache requires Android 4.0 or later.

### Install cache
You can install this cache with default cache dir size (10 MiB):
```java
SynergyKit.installCache(getApplicationContext());
```

Or you can install this cache with your own cache dir size:
```java
long cacheSize = 8 * 1024 * 1024; //8 MiB
SynergyKit.installCache(getApplicationContext(), cacheSize);
```
###Flush cache
You can also flush installed cache:

```java
SynergyKit.flushCache();
```


## Changelog
### Version 2.1.4 (28. 4. 2015)

- **SynergyKit v2.1 support**
- Documents
- Real-time data observing
- Queries
- Users
- Platforms
- Roles
- Communication
- Files
- CloudCode
- Batching requests



## Author

<img src="http://letsgood.com/src/img/logo-letsgood.png" alt="SynergyKIT" title="SynergyKIT" width="120px">

Letsgood.com s.r.o., Prague, Heart of Europe - part of Etnetera Group.

development@letsgood.com, http://letsgood.com/en

## License

SynergyKit SDK Android is available under the Apache 2.0 licence. See the LICENSE file for more info.
