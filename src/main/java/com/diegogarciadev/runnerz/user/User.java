package com.diegogarciadev.runnerz.user;

/*
{
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  },
 */
public record User(
        Integer id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
    public User {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address must not be null");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Phone must not be null");
        }
        if (website == null) {
            throw new IllegalArgumentException("Website must not be null");
        }
        if (company == null) {
            throw new IllegalArgumentException("Company must not be null");
        }
    }
}

