/api/auth/signup
{  "firstName": "lital",
   "lastName": "Fine",
   "userName": "owner",
   "email": "lital@gmail.com",
   "password": 12345678,
   "role":["owner"],
   "paymentFullName":"Lital Fine",
   "paymentExpDate":"24-2024",
   "paymentCardNum":"12345678765458765"
}

/api/auth/login
{   
   "userName": "owner",
   "password": 12345678
}

/api/stores/store
{
    "name":"Home"
}

/api/stores/catalogs
{
    "name":"Home",
    "catalogsList":[
        {"itemsList":[{"name": "Chair", "price" : 500, "stock":30}, {"name": "Desk", "price" : 1200, "stock":40}, {"name": "Bag", "price" : 60, "stock":10}, {"name": "Lamp", "price" : 400, "stock":200}]},
	{"itemsList":[{"name": "Microwave", "price" : 2000, "stock":10}, {"name": "Toaster", "price" : 500, "stock":400}]}
        ]}

/api/stores/items
{
    "name":"Home",
    "catalogsList":[
        {"id":1, "itemsList":[{"name": "Pen", "price" : 5, "stock":300}, {"name": "Notebook", "price" : 10, "stock":400}, {"name": "Marker", "price" : 5, "stock":100}]}]
}

/api/stores/update/items
{
    "name":"Home",
    "catalogsList":[
        {"id":1, "itemsList":[{"id":2,"name": "Pen", "price" : 10}]}]
}

(DELETE)
/api/stores/catalogs
{
    "name":"Home",
    "catalogsList":[
        {"id":66}]
}

