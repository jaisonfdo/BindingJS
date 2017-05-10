# BindingJS
This is a sample Android application to show how to bind Javascript code to Android code.

![ScreenShot](http://droidmentor.com/wp-content/uploads/2017/05/binding.png)

For getting data from the webpage via Javascript follow the below step-by-step guidelines.

1. To use <B>WebView</B>, declare the following the following code in the xml file.

```
<WebView
    android:id="@+id/webView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
2. Then, add the following permissions to your <B>AndroidManifest.xml</B> file:

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
3. For the client, to receive callbacks from JS there must be a Javascript interface class inside it which will implement the callbacks. You must add @JavascriptInterface annotation to all the methods inside the JS interface class.

```
public class JavaScriptReceiver
{
   Context mContext;
 
   /** Instantiate the receiver and set the context */
   JavaScriptReceiver(Context c) {
      mContext = c;
   }
 
   @JavascriptInterface
   public void showShopping(){
     Intent intent = new Intent(mContext, ShoppingActivity.class);
     mContext.startActivity(intent);
   }
 
   @JavascriptInterface
   public void showOrders(int orderid){
     Intent intent = new Intent(mContext, MyOrdersActivity.class);
     intent.putExtra("order",orderid);
     mContext.startActivity(intent);
   }
}
```
4. Use the following snippet for initialising the webview.
```
  WebView webView;
 
  webView = (WebView) findViewById(R.id.webView);
  embed_link = "http://droidmentor-app-callback.surge.sh/";

  webView.setWebChromeClient(new WebChromeClient());
  webView.getSettings().setDomStorageEnabled(true);
  webView.getSettings().setJavaScriptEnabled(true);
```
5. Use the same interface and function name in the calling statement inside the Javascript.

```
<script>
 
   function getRandomNumber(min,max) {
     return Math.floor( Math.random() * ( 1 + min - max ) ) + max;
   }
 
   function showOrders() {
     var number = getRandomNumber(10000,50000)
     JSReceiver.showOrders(number);
   }
 
   function showShopping() {
     JSReceiver.showShopping();
   }
</script>
 
<a href="#" class="btn-view-orders" onclick="showOrders()">View Orders</a>
<a href="#" onclick="showShopping()">Continue Shopping</a>

```
6. For binding the JS into your source code, use <B>addJavascriptInterface()</B>  method.
```
JavaScriptReceiver javaScriptReceiver;
javaScriptReceiver = new JavaScriptReceiver(this);
webView.addJavascriptInterface(javaScriptReceiver, "JSReceiver");
```

For more information, check out my detailed guide here : http://droidmentor.com/bind-javascript-to-android/
