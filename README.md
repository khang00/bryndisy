# Bryndisy
## API
* To get all user: __GET "/"__

* To get a user by ID: __GET "/?id=[USER_ID]"__

* To get a user with their optimized tasks: __GET "optimizedTasks/?id=[USER_ID]"__

* To create a user: __POST "/"__
 ```
{
    "name": "khang"
    "workDurationPerDay": "PT8H"
}
```

* To create a task for a user: __POST "/userTask"__
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