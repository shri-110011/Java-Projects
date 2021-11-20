MethodHandles vs Reflection:

MethodHandles API were introduced in Java 7 and is an upgrade to the Reflection API.

Reflection API is slow because for each method call of Reflection API, security checks happens internally to 
decide if the caller should be allowed to access the Reflection API's method on the subject or not.

If same code is calling the same ReflectionAPI's method again and again then for each of those request the 
security checks will take place and hence the performance of the Reflection API gets affected in terms of speed.  
 
The MethodHandles API overcomes this issue.

To work with MethodHandles API, we create a LookUp object which is a factory object using which we create the 
MethodHandle for the fields, methods & constructors of a class.

LookUp object is the entry point to the MethodHandles API and this object contains all the security related 
information.

Unlike the Reflection API where for the same requests, security checks were getting executed again and again 
for this MethodHandles API this LookUp object gets checked only once i.e. Security checks gets executed only 
once for same requests.