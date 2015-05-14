<h1 id="synergykit-android-sdk">SynergyKit Android SDK</h1>

<p align="left">
<img src="https://synergykit.blob.core.windows.net/synergykit/synergykitlogo.png" alt="Synergykit" title="Synergykit" width="33%">
</p>

<p>Letsgood.com runs Backend as a Service SynergyKit for <strong>fast and simple mobile/web/desktop applications development</strong>. SynergyKit allows enterpreneurs implement an idea to project fast and at low cost like Lean Startup.</p>

<p>We know how hard can be to work with untried API, so we prepared SDKs for mostly used platforms.</p>

<p><strong>Another SDKs</strong></p>

<ul>
<li><a href="https://github.com/SynergyKit/synergykit-sdk-ios">iOS SDK</a></li>
<li><a href="https://github.com/SynergyKit/synergykit-sdk-nodejs">Node.js SDK</a> <br>
<br></li>
</ul>

<p><strong>Table of content</strong></p>

<p><div class="toc">
<ul>
<li><a href="#synergykit-android-sdk">SynergyKit Android SDK</a><ul>
<li><a href="#sample-application">Sample Application</a><ul>
<li><a href="#sample-app-installation">Sample App Installation</a></li>
</ul>
</li>
<li><a href="#installation">Installation</a></li>
<li><a href="#architecture">Architecture</a><ul>
<li><a href="#building-model">Building model</a></li>
</ul>
</li>
<li><a href="#synergykit-initialization">SynergyKit Initialization</a></li>
<li><a href="#responses-handling">Responses handling</a></li>
<li><a href="#documents">Documents</a><ul>
<li><a href="#create-new-document">Create new document</a></li>
<li><a href="#retrieve-an-existing-document-by-id">Retrieve an existing document by ID</a></li>
<li><a href="#update-document">Update document</a></li>
<li><a href="#delete-document">Delete document</a></li>
</ul>
</li>
<li><a href="#real-time-data-observerving">Real-time data observerving</a><ul>
<li><a href="#connect">Connect</a></li>
<li><a href="#disconnect">Disconnect</a></li>
<li><a href="#checking-connection-state">Checking connection state</a></li>
<li><a href="#start-observing-whole-collection">Start observing whole collection</a></li>
<li><a href="#start-observing-collection-with-filter">Start observing collection with filter</a></li>
<li><a href="#stop-observing">Stop observing</a></li>
<li><a href="#speak-communication">Speak communication</a><ul>
<li><a href="#send-speak">Send speak</a></li>
<li><a href="#receive-speak">Receive speak</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#queries">Queries</a><ul>
<li><a href="#available-conditions">Available conditions</a><ul>
<li><a href="#filter">filter</a></li>
<li><a href="#startswith">startswith</a></li>
<li><a href="#endswith">endswith</a></li>
<li><a href="#substringof">substringof</a></li>
<li><a href="#in">in</a></li>
<li><a href="#nin">nin</a></li>
<li><a href="#select">select</a></li>
<li><a href="#top">top</a></li>
<li><a href="#orderby">orderby</a></li>
<li><a href="#inlinecount">inlinecount</a></li>
<li><a href="#skip">skip</a></li>
</ul>
</li>
<li><a href="#querying-objects">Querying objects</a></li>
<li><a href="#list-all-users">List all users</a></li>
<li><a href="#list-all-documents">List all documents</a></li>
<li><a href="#list-all-files">List all files</a></li>
</ul>
</li>
<li><a href="#users">Users</a><ul>
<li><a href="#create-a-new-user">Create a new user</a></li>
<li><a href="#retrieve-an-existing-user-by-id">Retrieve an existing user by ID</a></li>
<li><a href="#update-user">Update user</a></li>
<li><a href="#delete-user">Delete user</a></li>
<li><a href="#add-role">Add role</a></li>
<li><a href="#remove-role">Remove role</a></li>
<li><a href="#add-platform-to-user">Add platform to user</a></li>
<li><a href="#retrive-platform">Retrive platform</a></li>
<li><a href="#update-platform">Update platform</a></li>
<li><a href="#delete-platform">Delete platform</a></li>
<li><a href="#activating-user">Activating user</a></li>
<li><a href="#login-user">Login user</a></li>
</ul>
</li>
<li><a href="#communication">Communication</a><ul>
<li><a href="#send-notification">Send notification</a></li>
<li><a href="#send-e-mail">Send e-mail</a></li>
</ul>
</li>
<li><a href="#files">Files</a><ul>
<li><a href="#upload-file">Upload file</a></li>
<li><a href="#retrieve-file-by-id">Retrieve file by ID</a></li>
<li><a href="#delete-file">Delete file</a></li>
</ul>
</li>
<li><a href="#cloud-code">Cloud Code</a><ul>
<li><a href="#run-cloud-code">Run cloud code</a></li>
</ul>
</li>
<li><a href="#batch-request">Batch request</a><ul>
<li><a href="#batchitem">BatchItem</a></li>
<li><a href="#adding-to-batch">Adding to batch</a></li>
<li><a href="#sending-batch">Sending batch</a></li>
</ul>
</li>
<li><a href="#cache">Cache</a><ul>
<li><a href="#install-cache">Install cache</a></li>
<li><a href="#flush-cache">Flush cache</a></li>
</ul>
</li>
<li><a href="#changelog">Changelog</a><ul>
<li><a href="#version-214-28-4-2015">Version 2.1.4 (28. 4. 2015)</a></li>
</ul>
</li>
<li><a href="#author">Author</a></li>
<li><a href="#license">License</a></li>
</ul>
</li>
</ul>
</div>
</p>



<h2 id="sample-application">Sample Application</h2>

<p>Almost all possibilities of SynergyKit are presented in Sample Application that was developed next to SDK as introduction of how it works.</p>



<h3 id="sample-app-installation">Sample App Installation</h3>

<ul>
<li>Clone or download the repository.</li>
<li>Open <code>Android Studio</code>.</li>
<li>Open project <code>SynergyKit SDK Android</code></li>
<li>Run Gradle task <code>installLocalSdkDebug</code> in module  <code>sample-app</code></li>
</ul>



<h2 id="installation">Installation</h2>

<p>SynergyKit SDK Android is available through <a href="https://bintray.com/letsgood/maven/synergykit-sdk-android/view">Maven</a> repository. <strong>Minimum Android SDK version is 14</strong>.</p>

<p>To install it, simply add the following lines to your build.gradle files:</p>



<pre class="prettyprint"><code class="language-java hljs ">repositories {
    maven {
        url  <span class="hljs-string">"http://dl.bintray.com/letsgood/maven"</span> 
    }
}</code></pre>



<pre class="prettyprint"><code class="language-java hljs ">compile <span class="hljs-string">'com.letsgood:synergykit-sdk-android:2.1.4'</span></code></pre>



<h2 id="architecture">Architecture</h2>



<h3 id="building-model">Building model</h3>

<p>SynergyKit has two base objects <code>SynergykitObject</code> and <code>SynergykitUser</code>.  Every object which you want to store in SynergyKit must extends <code>SynergykitObject</code>. Every user you want to sign in, sign out or store in SynergyKit must extends <code>SynergykitUser</code>.   </p>

<p>SDK uses <a href="https://code.google.com/p/google-gson/">Google Gson</a> to serialize object to JSON and deserialize object from JSON.  Every argument of your objects which would be serialize/deserialize must have <code>@Expose</code> annotation. </p>



<pre class="prettyprint"><code class="language-java hljs "><span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">DemoObject</span> <span class="hljs-keyword">extends</span> <span class="hljs-title">SynergykitObject</span> {</span>

    <span class="hljs-comment">/* Attributes */</span>
    <span class="hljs-annotation">@Expose</span>
    <span class="hljs-keyword">private</span> String text; <span class="hljs-comment">//this attribute will be serialize/deserialize</span>

    <span class="hljs-comment">/* Text getter */</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getText</span>() {
        <span class="hljs-keyword">return</span> text;
    }

    <span class="hljs-comment">/* Text setter */</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setText</span>(String text) {
        <span class="hljs-keyword">this</span>.text = text;
    }

}</code></pre>



<pre class="prettyprint"><code class="language-java hljs "><span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">DemoUser</span> <span class="hljs-keyword">extends</span> <span class="hljs-title">SynergykitUser</span> {</span>

    <span class="hljs-comment">/* Attributes */</span>
    <span class="hljs-annotation">@Expose</span>
    <span class="hljs-keyword">private</span> String name; <span class="hljs-comment">//this attribute will be serialize/deserialize</span>
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">int</span> age; <span class="hljs-comment">//this attribute will not be serialize/deserialize</span>

    <span class="hljs-comment">/* Name getter */</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getName</span>() {
        <span class="hljs-keyword">return</span> name;
    }

    <span class="hljs-comment">/* Name setter */</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setName</span>(String name) {
        <span class="hljs-keyword">this</span>.name = name;
    }

    <span class="hljs-comment">/* Age getter */</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getAge</span>() {
        <span class="hljs-keyword">return</span> age;
    }

    <span class="hljs-comment">/* Age setter */</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setAge</span>(<span class="hljs-keyword">int</span> age) {
        <span class="hljs-keyword">this</span>.age = age;
    }
}</code></pre>



<h2 id="synergykit-initialization">SynergyKit Initialization</h2>

<p>Before you can start with SynergyKit SDK you need to set tenant and key. Is recommended to set it up in <code>onCreate</code> method  the your application class (class extended Application).</p>

<p>You can find it in <strong>Settings &gt; Application keys &gt; Tenant</strong> and <strong>Settings &gt; Application keys &gt; Value</strong> in SynergyKit web application.</p>



<pre class="prettyprint"><code class="language-java hljs ">Synergykit.init(APPLICATION_TENANT, APPLICATION_KEY);</code></pre>



<h2 id="responses-handling">Responses handling</h2>

<p>There are many options that you can receive at the end of API communication. SDK provides many listeners to hadle responses.</p>

<p>Every request has response listener  which provides <code>doneCallback</code> and  <code>errorCallback</code>. <code>doneCallback</code> is called when everything was done without error. <code>errorCallback</code> otherwise.</p>

<p>For example base <code>ResponseListener</code> </p>



<pre class="prettyprint"><code class="language-java hljs "><span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">interface</span> <span class="hljs-title">ResponseListener</span> {</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject object);
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject);
}</code></pre>



<h2 id="documents">Documents</h2>

<p>Documents are data saved in collections. Collections are basically tables in database where you can store your data. By sending requests to the documents endpoint, you can list, create, update or delete documents.</p>



<h3 id="create-new-document">Create new document</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">collection</td>
  <td align="left">String</td>
  <td align="left">Location of document</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">object</td>
  <td align="left">SynergykitObject</td>
  <td align="left">SynergykitObject or object extended SynergykitObject</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">ResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.createRecord(<span class="hljs-string">"collection"</span>,<span class="hljs-keyword">new</span> SynergykitObject(),<span class="hljs-keyword">new</span> ResponseListener() {
   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject object) {
        <span class="hljs-comment">//your code</span>
   }

   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
        <span class="hljs-comment">//your code</span>
   }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="retrieve-an-existing-document-by-id">Retrieve an existing document by ID</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">collection</td>
  <td align="left">String</td>
  <td align="left">Location of document</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">recordId</td>
  <td align="left">String</td>
  <td align="left">Record identificator</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">type</td>
  <td align="left">Type</td>
  <td align="left">Object type</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">ResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.getRecord(<span class="hljs-string">"collection"</span>,<span class="hljs-string">"recordId"</span>,SynergykitObject.class,<span class="hljs-keyword">new</span> ResponseListener() {
  <span class="hljs-annotation">@Override</span>
  <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject object) {
    <span class="hljs-comment">//your code</span>
  }

  <span class="hljs-annotation">@Override</span>
  <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
    <span class="hljs-comment">//your code</span>
  }
},<span class="hljs-keyword">false</span>);</code></pre>



<h3 id="update-document">Update document</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">collection</td>
  <td align="left">String</td>
  <td align="left">Location of document</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">object</td>
  <td align="left">SynergykitObject</td>
  <td align="left">SynergykitObject or object extended SynergykitObject</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">type</td>
  <td align="left">Type</td>
  <td align="left">Object type</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">ResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs "> Synergykit.updateRecord(<span class="hljs-string">"collection"</span>,object,<span class="hljs-keyword">new</span> ResponseListener() {
     <span class="hljs-annotation">@Override</span>
     <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject object) {
        <span class="hljs-comment">//your code</span>
     }

     <span class="hljs-annotation">@Override</span>
     <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
        <span class="hljs-comment">//your code</span>
     }
 },<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="delete-document">Delete document</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">collection</td>
  <td align="left">String</td>
  <td align="left">Location of document</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">recordId</td>
  <td align="left">String</td>
  <td align="left">Record identificator</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">DeleteResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.deleteRecord(<span class="hljs-string">"collection"</span>,<span class="hljs-string">"recordId"</span>,<span class="hljs-keyword">new</span> DeleteResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {
        <span class="hljs-comment">//your code</span>
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
        <span class="hljs-comment">//your code</span>
    }
},<span class="hljs-keyword">false</span>);</code></pre>



<h2 id="real-time-data-observerving">Real-time data observerving</h2>

<p>SDK supports real time communication through sockets. With this funcion you can develop dynamic applications.</p>



<h3 id="connect">Connect</h3>

<p>To connect socket to SynergyKit server you must call SynergyKit.connect() method.</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergyKit.connectSocket();</code></pre>



<h3 id="disconnect">Disconnect</h3>

<p>To disconnect socket use method disconnectSocket();</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergyKit.disconnectSocket();</code></pre>



<h3 id="checking-connection-state">Checking connection state</h3>

<p>If you need to check states <code>connected</code>, <code>disconnected</code> and <code>reconnected</code> you can call connect method with listener.</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergyKit.connectSocket(<span class="hljs-keyword">new</span> SocketStateListener() {
   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">connected</span>() {
      <span class="hljs-comment">//this method is called when socket was connected</span>
   }

   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">disconnected</span>() {
     <span class="hljs-comment">//this method is called when socket was disconnected</span>
   }

   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">reconnected</span>() {
     <span class="hljs-comment">//this method is called when socket was reconnected</span>
   }
});</code></pre>



<h3 id="start-observing-whole-collection">Start observing whole collection</h3>

<p>SynergyKit provides listening changes of records in data collection. You can listen  <code>created</code>, <code>updated</code>, <code>patched</code>, <code>deleted</code> states.  You can set / unset listener(s) befor or after socket is connected (Both situation are supported).</p>



<pre class="prettyprint"><code class="language-java hljs ">
<span class="hljs-comment">/*
* If you wanna listen new posted records to collection demo_collection you can use this code.
*/</span>

<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String COLLECTION = <span class="hljs-string">"demo_collection"</span>;

.
.
.

SynergyKit.onSocket(Socket.MESSAGE_CREATED,COLLECTION,<span class="hljs-keyword">new</span> SocketEventListener() {
      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">call</span>(Object... args) {
        <span class="hljs-comment">// this method is called when new record was created in collection demo_collection</span>

      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">subscribed</span>() {
        <span class="hljs-comment">// this method is called when listener was subscribed and is ready to listen</span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unsubscribed</span>() {
        <span class="hljs-comment">// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed </span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unauthorized</span>() {
        <span class="hljs-comment">// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.</span>
      }
     });</code></pre>



<h3 id="start-observing-collection-with-filter">Start observing collection with filter</h3>

<p>SynergyKit provides listening changes of filtered collection records. Just create your filter and set socket listener.</p>



<pre class="prettyprint"><code class="language-java hljs ">
<span class="hljs-comment">/*
* If you wanna listen new posted records from user called TestUser on collection demo_collection you can use this code.
*/</span>

<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String COLLECTION = <span class="hljs-string">"demo_collection"</span>;
<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String FILTER_NAME = <span class="hljs-string">"filter_records_from_testuser"</span>;

.
.
.

<span class="hljs-comment">//Build your OData Filter</span>
ODataBuilder oDataBuilder = ODataBuilder.newInstance().setFilter(Filter.buildAttribute(<span class="hljs-string">"from"</span>),Filter.OPERATOR_EQUAL,Filter.buildParametr(<span class="hljs-string">"TestUser"</span>));

<span class="hljs-comment">//Create filter</span>
SynergyKitSocketFilter filter = <span class="hljs-keyword">new</span> SynergyKitSocketFilter(FILTER_NAME,oDataBuilder.build());

<span class="hljs-comment">//Set listener with filter</span>
SynergyKit.onSocket(Socket.MESSAGE_CREATED,COLLECTION, filter,<span class="hljs-keyword">new</span> SocketEventListener() {
      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">call</span>(Object... args) {
        <span class="hljs-comment">// this method is called when new record was created in collection demo_collection</span>

      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">subscribed</span>() {
        <span class="hljs-comment">// this method is called when listener was subscribed and is ready to listen</span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unsubscribed</span>() {
        <span class="hljs-comment">// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed </span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unauthorized</span>() {
        <span class="hljs-comment">// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.</span>
      }
     });</code></pre>



<h3 id="stop-observing">Stop observing</h3>

<p>If you don’t want to listen collection changes anymore you can call SynergyKit.offSocket(…) method.</p>



<pre class="prettyprint"><code class="language-java hljs ">
<span class="hljs-comment">/*
* If you wanna stop listen new posted records to collection demo_collection you can use this code.
*/</span>


<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String COLLECTION = <span class="hljs-string">"demo_collection"</span>;

.
.
.

SynergyKit.offSocket(Socket.MESSAGE_CREATED,COLLECTION);</code></pre>



<h3 id="speak-communication">Speak communication</h3>

<p>Communication without data storage from device to device.</p>



<h4 id="send-speak">Send speak</h4>



<pre class="prettyprint"><code class="language-java hljs ">
<span class="hljs-comment">/*
* For example if you're developing chat and you wanna send message about typing you can use this code.
*/</span>

<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String EVENT_TYPING = <span class="hljs-string">"typing"</span>;

.
.
.

Message message = <span class="hljs-keyword">new</span> Message();
message.setText(<span class="hljs-string">"TestUser is typing"</span>);

SynergyKit.emitViaSocket(EVENT_TYPING,message);</code></pre>



<h4 id="receive-speak">Receive speak</h4>



<pre class="prettyprint"><code class="language-java hljs ">
<span class="hljs-comment">/*
* For example if you're developing chat and you wanna listen state typing you can use this code.
*/</span>

<span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> String EVENT_TYPING = <span class="hljs-string">"typing"</span>;

.
.
.

SynergyKit.onSocket(EVENT_TYPING,<span class="hljs-keyword">new</span> SocketEventListener() {
      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">call</span>(Object... args) {
        <span class="hljs-comment">// this method is called when someone is typing - someone send emit typing.</span>

        String data =((JSONObject) args[<span class="hljs-number">0</span>]).toString();
        <span class="hljs-keyword">final</span> Message message = GsonWrapper.getGson().fromJson(data, Message.class);     

      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">subscribed</span>() {
        <span class="hljs-comment">// this method is called when listener was subscribed and is ready to listen</span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unsubscribed</span>() {
        <span class="hljs-comment">// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed </span>
      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">unauthorized</span>() {
        <span class="hljs-comment">// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.</span>
      }
     });</code></pre>



<h2 id="queries">Queries</h2>

<p>You can retrieve multiple objects at once by sending a request with query. If query has no conditions API returns simply lists of all objects in collection.</p>

<p>For more complex filtering and sorting SynergyKit accepts OData standard. These queries can be used with data, users and files.</p>



<h3 id="available-conditions">Available conditions</h3>

<p>Query string is builded according to <a href="http://odata.org">OData Protocol</a> and is appended to the end of the url.</p>

<p>The OData Protocol specification defines how to standardize a typed, resource-oriented CRUD interface for manipulating data sources by providing collections of entries which must have required elements.</p>



<h4 id="filter">filter</h4>

<p>Equivalent to if (field == “value” &amp;&amp; secondField &gt;= 33 || thirdField &lt; 132000).</p>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setFilter(Filter.newInstance()
                 .setFilter(Filter.buildAttribute(<span class="hljs-string">"field"</span>)
                 + Filter.buildParametr(<span class="hljs-string">"value"</span>)
                 + Filter.OPERATOR_AND 
                 + Filter.buildAttribute(<span class="hljs-string">"secondField"</span>) 
                 + Filter.OPERATOR_GREATER_THAN_OR_EQUAL 
                 + Filter.buildParametr(<span class="hljs-number">33</span>)
                 + Filter.OPERATOR_OR
                 + Filter.buildAttribute(<span class="hljs-string">"thirdField"</span>) 
                 + Filter.OPERATOR_LESS_THAN 
                 + Filter.buildParametr(<span class="hljs-number">132000</span>)));</code></pre>

<p>Available relation operators</p>

<ul>
<li><code>==</code> or <code>eq</code></li>
<li><code>!=</code> or <code>ne</code></li>
<li><code>&gt;=</code> or <code>ge</code></li>
<li><code>&lt;=</code> or <code>le</code></li>
<li><code>&gt;</code> or <code>gt</code></li>
<li><code>&lt;</code> or <code>lt</code></li>
</ul>



<h4 id="startswith">startswith</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setFilter(Filter
            .newInstance()
            .setFilter(Filter.buildStartsWithFilter(<span class="hljs-string">"name"</span>,<span class="hljs-string">"a"</span>)));</code></pre>



<h4 id="endswith">endswith</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setFilter(Filter
            .newInstance()
            .setFilter(Filter.buildEndsWithFilter(<span class="hljs-string">"name"</span>,<span class="hljs-string">"z"</span>)));</code></pre>



<h4 id="substringof">substringof</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setFilter(Filter
            .newInstance()
            .setFilter(Filter.buildSubStringOfFilter(<span class="hljs-string">"name"</span>,<span class="hljs-string">"bc"</span>)));</code></pre>



<h4 id="in">in</h4>



<pre class="prettyprint"><code class="language-java hljs "> String[] names = <span class="hljs-keyword">new</span> String[<span class="hljs-number">2</span>];
        names[<span class="hljs-number">0</span>] = <span class="hljs-string">"Lucas"</span>;
        names[<span class="hljs-number">1</span>] = <span class="hljs-string">"Thomas"</span>;

uriBuilder.setFilter(Filter
            .newInstance()
            .setFilter(<span class="hljs-string">"name"</span>,Filter.OPERATOR_IN,Filter.buildArrayParameter(names)));</code></pre>



<h4 id="nin">nin</h4>



<pre class="prettyprint"><code class="language-java hljs ">String[] names = <span class="hljs-keyword">new</span> String[<span class="hljs-number">2</span>];
        names[<span class="hljs-number">0</span>] = <span class="hljs-string">"John"</span>;
        names[<span class="hljs-number">1</span>] = <span class="hljs-string">"Mark"</span>;

uriBuilder.setFilter(Filter.newInstance()           .setFilter(<span class="hljs-string">"name"</span>,Filter.OPERATOR_NOT_IN,Filter.buildArrayParameter(names)));</code></pre>



<h4 id="select">select</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.addSelect(<span class="hljs-string">"firstName"</span>)
             .addSelect(<span class="hljs-string">"lastName"</span>);</code></pre>



<h4 id="top">top</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setTop(<span class="hljs-number">5</span>);</code></pre>



<h4 id="orderby">orderby</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setOrderByAsc(<span class="hljs-string">"name"</span>);</code></pre>



<h4 id="inlinecount">inlinecount</h4>



<pre class="prettyprint"><code class="language-java hljs "> uriBuilder.setEnabled(<span class="hljs-keyword">true</span>);</code></pre>



<h4 id="skip">skip</h4>



<pre class="prettyprint"><code class="language-java hljs ">uriBuilder.setSkip(<span class="hljs-number">32</span>);</code></pre>



<h3 id="querying-objects">Querying objects</h3>

<p>If query is prepared, you just call some of SynergyKit methods.</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergykitConfig config = SynergykitConfig
                         .newInstance()
                         .setUri(uriBuilder.build())
                         .setParallelMode(<span class="hljs-keyword">false</span>)
                         .setType(SynergykitObject[].class);

Synergykit.getRecords(config,<span class="hljs-keyword">new</span> RecordsResponseListener() {
 <span class="hljs-annotation">@Override</span>
 <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject[] objects) {

 }

 <span class="hljs-annotation">@Override</span>
 <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

 }
});</code></pre>



<h3 id="list-all-users">List all users</h3>

<p><code>SQuery</code> with <code>SynergykitUser</code> object without conditions.</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergykitUri synergykitUri = UriBuilder
                                 .newInstance()
                                 .setResource(Resource.RESOURCE_USERS)
                                 .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(<span class="hljs-keyword">false</span>)
                                .setType(SynergykitUser[].class)
                                .setUri(synergykitUri);

Synergykit.getUsers(config,<span class="hljs-keyword">new</span> UsersResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser[] users) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
});</code></pre>



<h3 id="list-all-documents">List all documents</h3>



<pre class="prettyprint"><code class="language-java hljs ">SynergykitUri synergykitUri = UriBuilder
                                 .newInstance()
                                 .setResource(Resource.RESOURCE_DATA)
                                 .setCollection(<span class="hljs-string">"collection"</span>)
                                 .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(<span class="hljs-keyword">false</span>)
                                .setType(SynergykitObject[].class)
                                .setUri(synergykitUri);

Synergykit.getRecords(config,<span class="hljs-keyword">new</span> RecordsResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject[] users) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
});</code></pre>



<h3 id="list-all-files">List all files</h3>



<pre class="prettyprint"><code class="language-java hljs ">SynergykitUri synergykitUri = UriBuilder
                                      .newInstance()
                                      .setResource(Resource.RESOURCE_FILES)
                                      .build();

SynergykitConfig config = SynergykitConfig
                                 .newInstance()
                                 .setParallelMode(<span class="hljs-keyword">false</span>)
                                .setType(SynergykitFile[].class)
                                .setUri(synergykitUri);

Synergykit.getFiles(config, <span class="hljs-keyword">new</span> FilesResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitFile[] users) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
});</code></pre>



<h2 id="users">Users</h2>

<p>Users are alfa and omega of every application. In SynergyKit you can easily work with your users by methods listed below.</p>



<h3 id="create-a-new-user">Create a new user</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser or object extended SynergykitUser</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">SynergykitUser user = <span class="hljs-keyword">new</span> SynergykitUser();
user.setEmail(<span class="hljs-string">"user@domain.com"</span>);
user.setPassword(<span class="hljs-string">"password"</span>);

Synergykit.createUser(user,<span class="hljs-keyword">new</span> UserResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {
        <span class="hljs-comment">//your code</span>
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
        <span class="hljs-comment">//your code</span>
    }
},<span class="hljs-keyword">false</span>);</code></pre>

<h3 id="retrieve-an-existing-user-by-id">Retrieve an existing user by ID</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">userId</td>
  <td align="left">String</td>
  <td align="left">User identification</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">type</td>
  <td align="left">Type</td>
  <td align="left">User object type</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.getUser(<span class="hljs-string">"userId"</span>,SynergykitUser.class,<span class="hljs-keyword">new</span> UserResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {
        <span class="hljs-comment">//your code</span>
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {
        <span class="hljs-comment">//your code</span>
    }
},<span class="hljs-keyword">false</span>);</code></pre>



<h3 id="update-user">Update user</h3>

<p>Save method executes <code>PUT</code> request if <code>_id</code> is set. </p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser or object extended SynergykitUser</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.updateUser(<span class="hljs-keyword">new</span> SynergykitUser(),<span class="hljs-keyword">new</span> UserResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="delete-user">Delete user</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser or object extended SynergykitUser to delete</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.deleteUser(user,<span class="hljs-keyword">new</span> DeleteResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="add-role">Add role</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser or object extended SynergykitUser</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">role</td>
  <td align="left">String</td>
  <td align="left">Role define in SynergyKit</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.addRole(user,<span class="hljs-string">"role"</span>,<span class="hljs-keyword">new</span> UserResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="remove-role">Remove role</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser or object extended SynergykitUser</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">role</td>
  <td align="left">String</td>
  <td align="left">Role define in SynergyKit</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.removeRole(user,<span class="hljs-string">"role"</span>,<span class="hljs-keyword">new</span> UserResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="add-platform-to-user">Add platform to user</h3>

<p>Platforms are useful for pairing individual mobile devices or web applications to the user via registration ID. After assignment platform to the user you will be able to send push notifications to the device or application.</p>

<p><strong>Before you can work with platforms</strong> of user is needed to login first. After successful login SDK receives sessionToken for authentication of user. Token is held by the SDK and is automatically inserted into the Headers.</p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">platform</td>
  <td align="left">SynergykitPlatform</td>
  <td align="left"></td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">PlatformResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.addPlatform(platform,<span class="hljs-keyword">new</span> PlatformResponseListener() {
        <span class="hljs-annotation">@Override</span>
        <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitPlatform platform) {

        }

        <span class="hljs-annotation">@Override</span>
        <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

        }
    },<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="retrive-platform">Retrive platform</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">platformId</td>
  <td align="left">String</td>
  <td align="left"></td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">PlatformResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.getPlatform(platformId<span class="hljs-string">",new PlatformResponseListener() {
    @Override
    public void doneCallback(int statusCode, SynergykitPlatform platform) {

    }

    @Override
    public void errorCallback(int statusCode, SynergykitError errorObject) {

    }
},true);</span></code></pre>



<h3 id="update-platform">Update platform</h3>

<p>Platforms contain of a few parameters but only two are updatable. Save method executes <code>PUT</code> request if <code>_id</code> is set, it could change <code>development</code> and <code>registrationId</code>. </p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">platform</td>
  <td align="left">SynergykitPlatform</td>
  <td align="left"></td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">PlatformResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.updatePlatform(platform,<span class="hljs-keyword">new</span> PlatformResponseListener() {
   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitPlatform platform) {

   }

   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

   }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="delete-platform">Delete platform</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">platformId</td>
  <td align="left">String</td>
  <td align="left"></td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">DeleteResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.deletePlatform(platformId,<span class="hljs-keyword">new</span> DeleteResponseListener() {
      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {

      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

      }
  },<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="activating-user">Activating user</h3>

<p>By default, user is not activated. This mean, that you can use this state to validate user e-mail address by sending him activation link.</p>

<p>To activate user, send an email with this activation link /v2.1/users/activation/[ACTIVATION_HASH]. You can provide parameter callback with url address where you want to redirect user after activation.</p>

<p>Or <strong>if you know that e-mai address is valid</strong> you can activate user with SDK.</p>



<pre class="prettyprint"><code class="language-java hljs ">user.setActivated(<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="login-user">Login user</h3>

<p>If user was registrated via normal way, which means by email and password, you can authenticate him with login method.</p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">user</td>
  <td align="left">SynergykitUser</td>
  <td align="left">SynergykitUser with email and password</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">UserResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.loginUser(user,<span class="hljs-keyword">new</span> UserResponseListener() {
      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitUser user) {

      }

      <span class="hljs-annotation">@Override</span>
      <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

      }
  });</code></pre>



<h2 id="communication">Communication</h2>

<p>In SynergyKit you can communicate with your users by different ways. There are listed some methods below this section.</p>

<p>One way is to sending push notifications into user devices. This action need to have filled your API key for Android devices in Settings, section Android. For push notifications into iOS devices you need to fill your password and certificates into Apple section in Settings.</p>

<p>Another way is to sending emails to your users. For this you need to create email templates in administration under Mailing section.</p>



<h3 id="send-notification">Send notification</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">notification</td>
  <td align="left">NSArray</td>
  <td align="left">List of recipient</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">NotificationResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">SynergykitNotification notification = SynergykitNotification
                                        .newInstance()
                                        .setAlert(<span class="hljs-string">"My notification"</span>)
                                        .addUserId(<span class="hljs-string">"userId"</span>)
                                        .setPayload(<span class="hljs-string">"payload"</span>);

Synergykit.sendNotification(notification,<span class="hljs-keyword">new</span> NotificationResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h3 id="send-e-mail">Send e-mail</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">templateName</td>
  <td align="left">String</td>
  <td align="left">Name of e-mail template from SynergyKit</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">email</td>
  <td align="left">SynergykitEmail</td>
  <td align="left">E-mail information</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">EmailResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left">Indicates whether the requests are provided in parallel or in series</td>
  <td align="center"><strong>required</strong></td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">SynergykitEmail email = SynergykitEmail
                               .newInstace()
                               .setEmail(<span class="hljs-string">"name@domain.com"</span>)
                               .setSubject(<span class="hljs-string">"subject"</span>)
                               .setFrom(<span class="hljs-string">"your-email@domain.com"</span>);

   Synergykit.sendEmail(<span class="hljs-string">"templateName"</span>,email,<span class="hljs-keyword">new</span> EmailResponseListener() {
       <span class="hljs-annotation">@Override</span>
       <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {

       }

       <span class="hljs-annotation">@Override</span>
       <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

       }
   },<span class="hljs-keyword">false</span>);</code></pre>

<p>E-mail template should looks like this example.</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-tag">&lt;<span class="hljs-title">p</span>&gt;</span>Hello %name%,<span class="hljs-tag">&lt;/<span class="hljs-title">p</span>&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">br</span>&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">p</span>&gt;</span>this e-mail was send from SynergyKit Sample Application.<span class="hljs-tag">&lt;/<span class="hljs-title">p</span>&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">br</span>&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">p</span>&gt;</span>SynergyKit Team<span class="hljs-tag">&lt;/<span class="hljs-title">p</span>&gt;</span></code></pre>



<h2 id="files">Files</h2>

<p>SynergyKit can be also used for storing as much quantity of files as you need for your application.</p>



<h3 id="upload-file">Upload file</h3>

<p>SynergyKit Android SDK supports upload bitmaps and byte array. If file is successfully uploaded <code>SynergykitFile</code> representing just created file object is returned. <code>SynergykitFile</code> contains path to file from where is file accessible.</p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">bitmap</td>
  <td align="left">Bitmap</td>
  <td align="left">Bitmap to upload</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">FileResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs "><span class="hljs-comment">/*
* Example of bitmap uploading
*/</span>

Synergykit.createFile(bitmap,<span class="hljs-keyword">new</span> FileResponseListener() {
   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitFile file) {

   }

   <span class="hljs-annotation">@Override</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

   }
});</code></pre>



<h3 id="retrieve-file-by-id">Retrieve file by ID</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">fileId</td>
  <td align="left">String</td>
  <td align="left">File identification</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">FileResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.getFile(<span class="hljs-string">"fileId"</span>,<span class="hljs-keyword">new</span> FileResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitFile file) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">false</span>);</code></pre>



<h3 id="delete-file">Delete file</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">fileId</td>
  <td align="left">String</td>
  <td align="left">File identification</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">DeleteResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.deleteFile(<span class="hljs-string">"fileId"</span>,<span class="hljs-keyword">new</span> DeleteResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h2 id="cloud-code">Cloud Code</h2>

<p>Our vision is to let developers build any app without dealing with servers. For complex apps, sometimes you just need a bit of logic that isn’t running on a mobile device. Cloud Code makes this possible.</p>

<p>Cloud Code runs in the Node.js jailed sandbox and uses strict JavaScript language with some prepared modules and variables, which you can use for your development.</p>



<h3 id="run-cloud-code">Run cloud code</h3>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">cloudCode</td>
  <td align="left">SynergykitCloudCode</td>
  <td align="left">SynergykitCloudCode or object extended SynergykitCloudCode with other parameters</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">type</td>
  <td align="left">Type</td>
  <td align="left">Return object type (Must extend SynergykitObject)</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">ResponseListener()</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs "> SynergykitCloudCode cloudCode = SynergykitCloudCode.newInstance(<span class="hljs-string">"cloudCodeName"</span>);

 Synergykit.invokeCloudCode(cloudCode, SynergykitObject.class,<span class="hljs-keyword">new</span> ResponseListener() {
     <span class="hljs-annotation">@Override</span>
     <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitObject object) {

     }

     <span class="hljs-annotation">@Override</span>
     <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

     }
 },<span class="hljs-keyword">true</span>);</code></pre>

<p>Example cloud code function should looks like this.</p>



<pre class="prettyprint"><code class=" hljs erlang"><span class="hljs-function"><span class="hljs-title">callback</span><span class="hljs-params">(<span class="hljs-string">"Hello "</span> + parameters.name + <span class="hljs-string">"!"</span>)</span></span></code></pre>



<h2 id="batch-request">Batch request</h2>

<p>We know that internet connection is sometimes unstable and we know it’s not really good for synchronization algorithm where dozens of requests need to be executed without mistake. Batch request minimizes risk with connection failure - it’s all in one or nothing, not first five request, then two failed (walk under the bridge) and at the end three successful.</p>



<h3 id="batchitem">BatchItem</h3>

<p>You can batch every request you can imagine with <code>SynergykitBatchItem</code> object. At first create batch item that says where and how to do it.</p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">method</td>
  <td align="left">String</td>
  <td align="left">REST method</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">endpoint</td>
  <td align="left">SynergykitEndpoint</td>
  <td align="left">REST API endpoint</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">body</td>
  <td align="left">Child of SynergykitObject</td>
  <td align="left">POST request body</td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">SynergykitEndpoint endpoint = UriBuilder.newInstance()
                                        .setResource(Resource.RESOURCE_DATA)
                                        .setRecordId(<span class="hljs-string">"resourceId"</span>)
                                        .buildEndpoint();

SynergykitBatchItem batchItem = <span class="hljs-keyword">new</span> SynergykitBatchItem(Synergykit.GET,endpoint);</code></pre>



<h3 id="adding-to-batch">Adding to batch</h3>

<p>Every batch item need to be add to batch which you can send. At firt you must initialize batch. Then you can add BatchItems and send them all together.</p>



<pre class="prettyprint"><code class="language-java hljs ">Synergykit.initBatch(<span class="hljs-string">"batchId"</span>);

Synergykit.getBatch(<span class="hljs-string">"batchId"</span>).add(batchItem-<span class="hljs-number">0</span>);
Synergykit.getBatch(<span class="hljs-string">"batchId"</span>).add(batchItem-<span class="hljs-number">1</span>);
.
.
.
Synergykit.getBatch(<span class="hljs-string">"batchId"</span>).add(batchItem-n);</code></pre>



<h3 id="sending-batch">Sending batch</h3>

<p>Batch executes every request in the order in which they were added.</p>

<table>
<thead>
<tr>
  <th align="left">Parameter</th>
  <th align="left">Type</th>
  <th align="left">Notes</th>
  <th align="center"></th>
</tr>
</thead>
<tbody><tr>
  <td align="left">batchId</td>
  <td align="left">String</td>
  <td align="left">Batch identification</td>
  <td align="center"><strong>required</strong></td>
</tr>
<tr>
  <td align="left">listener</td>
  <td align="left">BatchResponseListener</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
<tr>
  <td align="left">parallelMode</td>
  <td align="left">boolean</td>
  <td align="left"></td>
  <td align="center">optional</td>
</tr>
</tbody></table>




<pre class="prettyprint"><code class="language-java hljs ">Synergykit.sendBatch(<span class="hljs-string">"batchId"</span>,<span class="hljs-keyword">new</span> BatchResponseListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">doneCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitBatchResponse[] batchResponse) {

    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">errorCallback</span>(<span class="hljs-keyword">int</span> statusCode, SynergykitError errorObject) {

    }
},<span class="hljs-keyword">true</span>);</code></pre>



<h2 id="cache">Cache</h2>

<p>The SynergyKit Android SDK provides Http response cache (HttpResponseCache). Http response cache caches all of your application’s HTTP requests. This cache requires Android 4.0  or later.</p>



<h3 id="install-cache">Install cache</h3>

<p>You can install this cache with default cache dir size (10 MiB):</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergyKit.installCache(getApplicationContext());</code></pre>

<p>Or you can install this cache with your own cache dir size:</p>



<pre class="prettyprint"><code class="language-java hljs "><span class="hljs-keyword">long</span> cacheSize = <span class="hljs-number">8</span> * <span class="hljs-number">1024</span> * <span class="hljs-number">1024</span>; <span class="hljs-comment">//8 MiB</span>
SynergyKit.installCache(getApplicationContext(), cacheSize);</code></pre>



<h3 id="flush-cache">Flush cache</h3>

<p>You can also flush installed cache:</p>



<pre class="prettyprint"><code class="language-java hljs ">SynergyKit.flushCache();</code></pre>



<h2 id="changelog">Changelog</h2>



<h3 id="version-214-28-4-2015">Version 2.1.4 (28. 4. 2015)</h3>

<ul>
<li><strong>SynergyKit v2.1 support</strong></li>
<li>Documents</li>
<li>Real-time data observing</li>
<li>Queries</li>
<li>Users</li>
<li>Platforms</li>
<li>Roles</li>
<li>Communication</li>
<li>Files</li>
<li>CloudCode</li>
<li>Batching requests</li>
</ul>



<h2 id="author">Author</h2>

<p><img src="http://letsgood.com/src/img/logo-letsgood.png" alt="SynergyKIT" title="SynergyKIT" width="10%"> </p>

<p>Letsgood.com s.r.o., Prague, Heart of Europe â€“ part of Etnetera Group.</p>

<p>development@letsgood.com, <a href="http://letsgood.com/en">http://letsgood.com/en</a></p>



<h2 id="license">License</h2>

<p>SynergyKit SDK Android is available under the Apache 2.0 licence. See the LICENSE file for more info.</p>