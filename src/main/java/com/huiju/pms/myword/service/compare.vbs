Dim ObjWD,ObjDOC
Set args = WScript.Arguments

Dim word1,word2

If args.Count = 3 Then
    word1= WScript.Arguments(0)
    word2= WScript.Arguments(1)    
    word3= WScript.Arguments(2) 
 


Set ObjWD=CreateObject("Word.application")

Set ObjDOC=ObjWD.Documents.Open(word1)

ObjDOC.Compare(word2)
ObjWD.ActiveDocument.SaveAs(word3)
 

ObjDOC.close
 
ObjWD.Quit

End If