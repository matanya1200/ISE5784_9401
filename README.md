דוח פרויקט

מתניה נבט 318379404

בנג'מין דווילה

פרטי התמונה:
התמונה הזו מוצלמת מהנקדודה

![image](https://github.com/user-attachments/assets/b38d1641-d3b9-4dcf-9457-9e3342f913c7)

יצירת גוף איש השלג:

![image](https://github.com/user-attachments/assets/2346de9f-a2de-4a7e-89e4-f60be93b4645)

יצירת העניים והפה:

![image](https://github.com/user-attachments/assets/1cd35df5-f504-4db9-b684-6a832b6dc280)

יצירת הזרועות:

![image](https://github.com/user-attachments/assets/a633da30-941d-4cbf-9ee2-f5f76048ef1e)

הוספת הכפתורים:

![image](https://github.com/user-attachments/assets/f4617856-641f-4c8b-9dbb-7deeab517f77)

הספת פתיתי שלג ועננים:

![image](https://github.com/user-attachments/assets/d3179b05-f792-4a89-9c80-4c9887739e4b)

הוספת מקורות התאורה:

![image](https://github.com/user-attachments/assets/12ead4ae-752b-416f-b5ee-b69ff4690636)

כך נראת התמונה הסופית:

![image](https://github.com/user-attachments/assets/8b42efba-c1f8-4163-ab6b-f91d49f66546)

ע"י הזזת המצלמה לנקדה הזו עם ווקטורי הכיוון האלו:


![image](https://github.com/user-attachments/assets/ec6ee453-c837-4178-9336-f123cf5476bd)

נקבל את התמונה הזו

![image](https://github.com/user-attachments/assets/d24c7406-612b-40ce-8967-80ee5ca3c907)



שיפור התמונה 
ANTI ALIASING:

תיאור הבעיה: המעבר בין צבע לצבע נעשה באופן ישיר מה שיוצר לתמונה קצוות חדים

פיתרון הבעיה: ניצור פיקסלי משנה שנמצאים בין שני פיקסלים ובכל פיקסל במקום לשלוח קרן יחידה נשלח הרבה קרנים שיחסו את כל הפקסל משנה 
לאחר מכן נעשה ממוצע על כל בצבעים שקבלנו מהקרנים ואם הצבע "רחוק" מהבסע המקורי של הפיקסל נצבע אותה הפקסל בצבע של ממצע הקרניים

ללא שיפור:

![image](https://github.com/user-attachments/assets/0d16e16f-2426-4af7-b51d-2159a3ff4175)


עם שיפור:


![image](https://github.com/user-attachments/assets/9cdc8a63-caaa-415b-a9fa-df03c099c6d1)



כך נראת התמונה הסופית לאחר השיפור:


![image](https://github.com/user-attachments/assets/6dbb0b15-71ab-47f9-900d-f004a28c1355)


הקוד של MT בשימוש עם AA


![image](https://github.com/user-attachments/assets/c78c2d88-c9af-463b-9a44-52bf053293f1)
![image](https://github.com/user-attachments/assets/be72d0b9-2249-44cd-9b48-e79ba9c1f5d7)
![image](https://github.com/user-attachments/assets/57e0c2f8-7347-4ad6-ab59-0aad23dabe52)





זמן הרצה שימוש AA וללא שימוש בMT:

8 דקות ו20 שניות

זמן הרצה שימוש AA וגם בMT:

3 וחצי דקות

שיפוש של 4 דוקות ועשר שניות 
כלומר כמעט פי 2 וחצי מהר יותר


[PDD]
PL/SQL Developer Diagram File
Version=1


[General]
AutoScale=True
PageWidth=0
PageHeight=0


[Item1]
Caption=JOINEDPASSENGERS
Left=124
Top=0
Width=322
Height=342
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,136,94,52,46,310
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=JOINEDPASSENGERS
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=1
1=PASSENGER_ID
2=NUMBER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(100)
ObjectID=2
1=PASSENGER_NAME
2=VARCHAR2(100)
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(20)
ObjectID=3
1=PASSENGER_PHONE
2=VARCHAR2(20)
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(100)
ObjectID=4
1=PASSENGER_EMAIL
2=VARCHAR2(100)
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(20)
ObjectID=5
1=PASSPORT_NUMBER
2=VARCHAR2(20)
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=DATE
ObjectID=6
1=PASSENGER_BIRTHDATE
2=DATE
3=Y
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008477
2=PASSENGER_ID
3=P

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008477
2=PASSENGER_ID
3=unique

[Item2]
Caption=JOINEDBOOKING
Left=597
Top=0
Width=273
Height=484
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,93,88,52,46,261
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=JOINEDBOOKING
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=1
1=BOOKING_ID
2=NUMBER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=2
1=PASSENGER_ID
2=NUMBER
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=3
1=FLIGHT_ID
2=NUMBER
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(10)
ObjectID=4
1=SEAT_NUMBER
2=VARCHAR2(10)
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=DATE
ObjectID=5
1=BOOKING_DATE
2=DATE
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=6
1=JOURNEY_ID
2=NUMBER
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=7
1=SELLER_ID
2=NUMBER
3=Y
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER
ObjectID=8
1=TICKET_ID
2=NUMBER
3=Y
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008478
2=BOOKING_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=FK_FLIGHT
2=FLIGHT_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=FK_PASSENGER
2=PASSENGER_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=FK_SELLER
2=SELLER_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=FK_TICKET
2=TICKET_ID
3=R

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008478
2=BOOKING_ID
3=unique

[Item3]
Caption=FLIGHTS
Left=107
Top=377
Width=349
Height=510
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,123,123,52,46,337
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=FLIGHTS
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=FLIGHT_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(100)
ObjectID=2
1=FLIGHT_NUMBER
2=VARCHAR2(100)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=DATE
ObjectID=3
1=DEPARTURE_TIME
2=DATE
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=DATE
ObjectID=4
1=ARRIVAL_TIME
2=DATE
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=5
1=FLIGHT_STATUS
2=VARCHAR2(200)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=6
1=AIRLINE_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=7
1=AIRCRAFT_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=8
1=ARRIVAL_AIRPORT
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=9
1=DEPARTURE_AIRPORT
2=INTEGER
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008401
2=FLIGHT_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008402
2=DEPARTURE_AIRPORT
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008403
2=AIRLINE_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008404
2=AIRCRAFT_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008405
2=ARRIVAL_AIRPORT
3=R

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008401
2=FLIGHT_ID
3=unique

[Item4]
Caption=AIRCRAFT
Left=586
Top=800
Width=304
Height=259
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,92,94,52,46,292
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=AIRCRAFT
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=AIRCRAFT_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=2
1=AIRCRAFT_TYPE
2=VARCHAR2(200)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=3
1=CAPACITY
2=INTEGER
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008384
2=AIRCRAFT_ID
3=P

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008384
2=AIRCRAFT_ID
3=unique

[Item5]
Caption=AIRLINES
Left=595
Top=511
Width=296
Height=266
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,106,94,52,46,284
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=AIRLINES
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=AIRLINE_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=2
1=AIRLINE_NAME
2=VARCHAR2(200)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=3
1=ORIGIN_COUNTRY
2=VARCHAR2(200)
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008372
2=AIRLINE_ID
3=P

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008372
2=AIRLINE_ID
3=unique

[Item6]
Caption=AIRPORTS
Left=182
Top=936
Width=279
Height=259
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,94,94,52,46,267
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=AIRPORTS
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=AIRPORT_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=2
1=AIRPORT_NAME
2=VARCHAR2(200)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=3
1=LOCATION
2=VARCHAR2(200)
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008376
2=AIRPORT_ID
3=P

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008376
2=AIRPORT_ID
3=unique

[Item7]
Caption=CREWMEMBERS
Left=1061
Top=1040
Width=289
Height=257
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,79,94,52,46,277
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=CREWMEMBERS
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=CREW_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=2
1=CREW_NAME
2=VARCHAR2(200)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(200)
ObjectID=3
1=CREW_ROLE
2=VARCHAR2(200)
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008380
2=CREW_ID
3=P

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008380
2=CREW_ID
3=unique

[Item8]
Caption=WORKINGCREW
Left=575
Top=1190
Width=302
Height=285
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,76,116,52,46,290
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=WORKINGCREW
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=1
1=FLIGHT_ID
2=INTEGER
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=INTEGER
ObjectID=2
1=CREW_ID
2=INTEGER
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008416
2=FLIGHT_ID, CREW_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008417
2=FLIGHT_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008418
2=CREW_ID
3=R

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008416
2=FLIGHT_ID, CREW_ID
3=unique

[Item9]
Caption=TICKET
Left=1037
Top=732
Width=343
Height=292
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,161,94,52,54,331
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=TICKET
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=NUMBER(38)
ObjectID=1
1=TICKET_ID
2=NUMBER(38)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(255)
ObjectID=2
1=TICKET_TYPE
2=VARCHAR2(255)
3=
4='Regular'
5=
-Field=
ImageType=COLUMN
ImageSubType=FLOAT
ObjectID=3
1=TICKET_PRICE
2=FLOAT
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008422
2=TICKET_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=CHK_TICKET_PRICE_POSITIVE
2=TICKET_PRICE
3=C

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008422
2=TICKET_ID
3=unique

[Item10]
Caption=TICKETSELLER
Left=1038
Top=392
Width=343
Height=284
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,147,104,52,46,331
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=TICKETSELLER
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=NUMBER(38)
ObjectID=1
1=SELLER_ID
2=NUMBER(38)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(255)
ObjectID=2
1=SELLER_NAME
2=VARCHAR2(255)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=VARCHAR2(255)
ObjectID=3
1=SELLER_CONTACT
2=VARCHAR2(255)
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008439
2=SELLER_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=CHECK_SELLER_CONTACT
2=SELLER_CONTACT
3=C

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008439
2=SELLER_ID
3=unique

[Item11]
Caption=PAYMENT_REPORT
Left=1078
Top=0
Width=297
Height=335
ShowIcons=True
ShowHeadings=True
ShowComment=True
Comment=
Grid=36,23,96,78,52,46,285
Username=mnabet
Database=XE
ObjectType=TABLE
ObjectOwner=SYS
ObjectName=PAYMENT_REPORT
SubObject=
Overload=0

-FieldGroup=
Name=Description
Visible=True
1=Column
2=Type
3=Nullable
4=Default
5=Comments
-Field=
ImageType=COLUMN
ImageSubType=NUMBER(38)
ObjectID=1
1=PAYMENT_ID
2=NUMBER(38)
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=DATE
ObjectID=2
1=PAYMENT_DATE
2=DATE
3=
4=
5=
-Field=
ImageType=COLUMN
ImageSubType=NUMBER(38)
ObjectID=3
1=BOOKING_ID
2=NUMBER(38)
3=
4=
5=

-FieldGroup=
Name=Key
Visible=True
1=Key
2=Column(s)
3=Type
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008456
2=PAYMENT_ID
3=P
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=BOOKING_ID
2=BOOKING_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=SYS_C008457
2=BOOKING_ID
3=R
-Field=
ImageType=KEY
ImageSubType=
ObjectID=0
1=CHK_P_POSITIVE
2=PAYMENT_ID
3=C

-FieldGroup=
Name=Index
Visible=True
1=Index
2=Column(s)
3=Type
-Field=
ImageType=INDEX
ImageSubType=
ObjectID=0
1=SYS_C008456
2=PAYMENT_ID
3=unique

[Link1]
Item1=1
Field1=1
End1=2
Item2=0
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=FK_PASSENGER

[Link2]
Item1=1
Field1=2
End1=2
Item2=2
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=FK_FLIGHT

[Link3]
Item1=2
Field1=6
End1=2
Item2=3
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008404

[Link4]
Item1=2
Field1=5
End1=2
Item2=4
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008403

[Link5]
Item1=2
Field1=8
End1=2
Item2=5
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008402

[Link6]
Item1=2
Field1=7
End1=2
Item2=5
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008405

[Link7]
Item1=7
Field1=0
End1=2
Item2=2
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008417

[Link8]
Item1=7
Field1=1
End1=2
Item2=6
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=SYS_C008418

[Link9]
Item1=1
Field1=7
End1=2
Item2=8
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=FK_TICKET

[Link10]
Item1=1
Field1=6
End1=2
Item2=9
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=FK_SELLER

[Link11]
Item1=10
Field1=2
End1=2
Item2=1
Field2=0
End2=1
LineColor=255
LineSize=2
LineLabel=True
LabelText=BOOKING_ID

