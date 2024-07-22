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


יצירת עץ:

![image](https://github.com/user-attachments/assets/8877392b-ea9c-4926-8e66-3a465644d822)



הוספת מקורות התאורה:

![image](https://github.com/user-attachments/assets/12ead4ae-752b-416f-b5ee-b69ff4690636)

כך נראת התמונה הסופית:

![image](https://github.com/user-attachments/assets/c7eb4990-d308-48b9-9749-766d337702cf)


ע"י הזזת המצלמה לנקדה הזו עם ווקטורי הכיוון האלו:


![image](https://github.com/user-attachments/assets/ec6ee453-c837-4178-9336-f123cf5476bd)

נקבל את התמונה הזו

![image](https://github.com/user-attachments/assets/97f51de9-55d1-4de0-a419-62a11829d367)



מיניפ1:


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


![image](https://github.com/user-attachments/assets/92c24ed1-fd4f-414d-bc9a-31499b693b2c)



מיניפ2:


חלק1:

שיפור ביצועים MT:


שיפור ביצועים MT עובד בצורה כזו שבמקום שנעבור על כל הפיקסלים אחד אחד ורק כאשר נסיים עם פקסל 1 נעבור לבא אחריו עם שיפור MT אנחנו בעצם עובדים על כמה פקסלים במקביל במקום אחד אחד בקוד שלנו כמות התהלכים שפועלים במקביל היא נתונה מראש(מתקבלת מהמעבד בתחית הרצת הקוד) והיא מספר הליבות הפנויות(פזיות ווירטואליות)

הקוד של MT בשימוש עם AA


![image](https://github.com/user-attachments/assets/c78c2d88-c9af-463b-9a44-52bf053293f1)
![image](https://github.com/user-attachments/assets/be72d0b9-2249-44cd-9b48-e79ba9c1f5d7)
![image](https://github.com/user-attachments/assets/57e0c2f8-7347-4ad6-ab59-0aad23dabe52)





זמן הרצה שימוש AA וללא שימוש בMT:

16 דקות ו20 שניות

זמן הרצה שימוש AA וגם בMT:

6 דקות ו40 שניות



חלק2:

שיפור ביצועים בעזרת BVH:

שיפור BVH עובד בצורה כזו שעם יצירת הטסט כל הצורות הגיאומטריות נכנסות לתוך קוביה אחת ואז אנחנו בודקים האם יש צורות גואמטריות בקופיה הזו ואם יש אנחנו מחלקים את הקוביה ל2 ובודקים את 2 הקוביות החדשות אם באחת מהם אין יותר צורות גיאומריות אנחנו מפסקים לפצל ולבדוק את הקוביה הזו

שיפור זה בד"כ יעיל לתמונה עם הרבה מאוד צורות גיאומטריות (לממוקמות במקום אחד)

(זמני הריצה האלו הם עבור תמונה חדשה עם יותר צורות גיאמטריות ומשתכים משקפים ולכן הם ארוכים יותר)

זמן הרצה שימוש AA וBVH וללא MT:

12 דקות

זמן הרצה שימוש AA וBVH וMT:

6 דקות
