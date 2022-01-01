Online store that enable multiple sellers to set up shops that buyers can purchase.
https://theonlinefinestore.herokuapp.com/

POST: /api/auth/signup
{  "firstName": "my_firstname",
   "lastName": "my_lastname",
   "userName": "my_username",
   "email": "my_email",
   "password": "my_password",
   "role":["owner","customer"],
   "paymentFullName":"payment_fullname",
   "paymentExpDate":"payment_expirationdate",
   "paymentCardNum":"payment_cardnum"
}

POST: /api/auth/login
{   
   "userName": "my_username",
   "password": "my_password"
}

POST: /api/stores/store
{
    "name":"store_name"
}

POST: /api/stores/catalogs
{
    "name": "store_name",
    "catalogsList": [
        {
            "itemsList": [
                {
                    "name": "item_name",
                    "price": item_price,
                    "stock": item_stock
                }
            ]
        }
    ]
}
        
POST: /api/stores/items
{
    "name": "store_name",
    "catalogsList": [
        {
            "id":catalog_id,
            "itemsList": [
                {
                    "name": "item_name",
                    "price": item_price,
                    "stock":item_stock
                }
            ]
        }
    ]
}

POST: /api/stores/update/items
{
    "name": "store_name",
    "catalogsList": [
        {
            "id":catalog_id,
            "itemsList": [
                {
                    "id": item_id,
                    "name": "item_name",
                    "price": item_price,
                    "stock": item_stock
                }
            ]
        }
    ]
}

POST: api/stores/purchase

[[item_id,item_qauntity]]

GET: api/stores/catalog/{id}

GET: api/stores/catalogs

GET: api/stores/items

DELETE: /api/stores/catalogs
{
    "name": "store_name",
    "catalogsList": [
        {
            "id":catalog_id
        }
    ]
}

