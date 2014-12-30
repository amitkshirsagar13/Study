db.mycollection.find({}).forEach(function(doc) {
	var splitted = doc.productName + '|' + doc.color;
	db.mycollection.update({
		_id : doc._id
					}, {
						$set : {
							nameColor : splitted
						}
	});
});