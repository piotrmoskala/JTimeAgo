## JTimeAgo - description

Java library based on Objective-clibrary 

https://github.com/kevinlawler/NSDate-TimeAgo

It can be used everywhere where java is available. 

## Sample usage

JTimeAgo extends java.util.Date class.

if you got date stored in Data object You can simply wrap it :

        Date date = Calendar.getInstance().getTime();
        JTimeAgo jTimeAgo = new JTimeAgo(date);

and thats all you need, then you have to call a following method:  

        String time = jTimeAgo.getTimeAgo(); 



## Language support 


For now English and Polish are only supported languages. 
English is set as default
If you want to change language you can do it using provided constructor: 

        JTimeAgo jTimeAgo = new JTimeAgo(LanguageType.EN);
        JTimeAgo jTimeAgo = new JTimeAgo(new Date(), LanguageType.EN); 

or by calling 

        jTimeAgo.setLanguage(LanguageType.EN);