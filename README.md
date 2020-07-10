# Bryndisy
## API
* To get all user: __GET "/"__

* To create a user: __POST "/"__
 ```
{
    "name": "khang"
    "workDurationPerDay": "PT8H"
}
```

* to create a task for a user: __POST "/userTask"__
```
{
    "first" :"5f081393b15644322c2d4e47",
    "second": {
        "name": "say hi",
        "duration": "PT3H",
        "deadline": "2020-07-12T07:06:59.000"
    }
}
```