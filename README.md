# instaOAuth
instaOAuth experiment by elzakaria

Little sample to showcase how you can use the OAuth protocol against the instagram API :

1 -  Redirect the user to authorize us by Instagram and send the authorization code to redirect_uri

2 -  /redirect_uri endpoint receives the authorization code and ask for the access token that we store as a cookie so we dont have
to authenticate the user everytime.

3 - /hello endpoint hits the instagram api endpoint /users/self to retrieve information about the profile and shows bio and pseudo.


Configuration :

You have to replace in the file with you own values, better put the on your maven user settings files so you don't change the file itself while MVN building.

prop_client_id=${client_id}
prop_client_secret=${client_secret}
prop_grant_type=${grant_type}
prop_redirect_uri=${redirect_uri}

${client_id} : your registered client id in Instagram
${client_secret} : your client secret
${grant_type} : always authorization_code
{redirect_uri} : always http://yourapp.com/redirect_uri that you put on your instagram api configuration redirect uri.

Sample :
prop_client_id=JK848FJ4HR4848R4Z
prop_client_secret=LK93020202KDJEJFJ
prop_grant_type=authorization_code
prop_redirect_uri=http://localhost:9999/instaOAuth/redirect_uri


Main Dependencies :

* *HttpClient* to make Http request to the instagram api.

* *Spring MVC* to have our request and views.
