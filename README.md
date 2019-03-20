# Cityhub-core

Cityhub-core is a web application for managing places of interest in a city.

API is, currently, exposed through a two REST endpoint:

### Places within bounds

```
https://cityhub-core.herokuapp.com/api/v1/pois/within/?type=${poiType}&upper=${upperBounds}&lower=${lowerBounds}
```
PoiType can be bars, restaurants and fastfood.


Upper and lower bounds are map screen bounds in which we want to search for POI.

Example of returned places:
```
{
    "places": [
        {
            "id": "581c61ad4e1611000bf821d9",
            "loc": [
                45.8113626,
                15.976522299999942
            ]
        },
        {
            "id": "581c65354e1611000bf821db",
            "loc": [
                45.8109426,
                15.976356500000065
            ]
        },
        ...
    ]
}
```
### POI details

```
https://cityhub-core.herokuapp.com/api/v1/pois/{type}/{id}
```
Example of returned POI:
```
{
    "_id": {
        "$oid": "581c61ad4e1611000bf821d9"
    },
    "name": "Orient Express Caffe bar",
    "workingHours": "pon-čet:7.30-24;pet-sub:7.30-02;ned:12-23;",
    "latitude": 45.8113626,
    "generalCategory": {
        "delivery": false,
        "smoking": false,
        "terace": true,
        "wifi": false,
        "pets": false,
        "parking": false,
        "playground": false
    },
    "fullName": "Orient Express Caffe bar undefined",
    "specialCategory": {
        "sandwiches": false,
        "beerFriendly": false,
        "juice": false,
        "coctails": false
    },
    "phone": "098 917 0395",
    "address": "Teslina ulica 10, 10000, Zagreb, Hrvatska",
    "longitude": 15.976522299999942,
    "location": [
        45.8113626,
        15.976522299999942
    ]
}
```
