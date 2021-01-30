# TinderNews
A Tinder like news client

## Techniques
- UI with Jetpack Navigation Component --> MainActivity.java and layout, menu, navigation directory under /res 
- Network layer with Retrofit --> RetrofitClient.java

## Public APIs
The source of the news is from https://newsapi.org/. Tow endpoints are used here:
- https://newsapi.org/v2/everything
- https://newsapi.org/v2/top-headlines

My API_KEY is sent via header, not url parameters. (An Interceptor is implemented to do it)
