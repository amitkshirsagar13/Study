db.mycollection.find({}).forEach(function(doc) {
	var splitted = doc.CombinedField.split('|');
	doc.fieldOne = splitted[0];
	doc.fieldTwo = spllited[1];
	db.mycollection.update({
		_id : doc._id
					}, {
						$set : {
							fieldOne : doc.fieldOne,
							fieldTwo : doc.fieldTwo
						}
		});
});