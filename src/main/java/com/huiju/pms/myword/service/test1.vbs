  Set oWord = CreateObject("Word.Application")
  Set activeDocument = oWord.Documents.Open( "E:\Test1.doc")

  Set objDoc = oWord.ActiveDocument
  objDoc.Select

  Set objSelection = oWord.Selection
  Call objSelection.GoTo(1,1,2)


  objSelection.Bookmarks.Item("\Page").Range.Copy
  Set newDoc = oWord.Documents.Open( "E:\POC\Page2.doc")

 
  newDoc.Goto(1,1,1).Paste
  newDoc.Save

  oWord.Quit