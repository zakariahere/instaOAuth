# instaOAuth
instaOAuth experiment by elzakaria

Little sample to showcase how you can use the OAuth protocol against the instagram API :

1 -  Redirect the user to authorize us by Instagram and send the authorization code to redirect_uri

2 -  /redirect_uri endpoint receives the authorization code and ask for the access token that we store as a cookie so we dont have
to authenticate the user everytime.

3 - /hello endpoint hits the instagram api endpoint /users/self to retrieve information about the profile and shows bio and pseudo.


Main Dependencies :

* *HttpClient* to make Http request to the instagram api.

* *Spring MVC* to have our request and views.
