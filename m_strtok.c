 #include <stdio.h>   
 #include <string.h>

char * m_strtok(char *,char*);

 int main( ) 
{ 
   char *token = NULL; 
   char str1[] = "This,is,string;split,Test;Program!!!! end\n"; 
   char str2[] = ",;!"; 

   token = m_strtok( str1, str2 ); 

   while( token != NULL ) 
   { 
     printf( "token = %s\n", token ); 
     token = m_strtok( NULL, str2 ); 
  } 

  return 0; 
}

char * m_strtok(char *str, char *tok)
{
   char *strReturn = str, *tokTemp = tok;
   static char *strTemp;
   
   if (str != NULL) 
       strTemp = str;
   else
       strReturn= strTemp;
       
   if (*strTemp == '\0') 
       return NULL;

   while(*strTemp != '\0')
   {
        tok = tokTemp;
        while(*tok != '\0')   
        {
            if(*strTemp == *tok) 
            {
                *strTemp = '\0';
                strTemp++;
                return strReturn;
            }
            tok++;
        }
        strTemp ++;
   }
   
   return strReturn;
}
