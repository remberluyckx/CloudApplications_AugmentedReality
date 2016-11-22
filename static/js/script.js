/* global deepstream, angular */


angular
.module( 'ar-webend', ["xeditable"] )
.service( 'deepstream', function() {
	return deepstream( 'localhost:6020' )
		.login( {username: 'devuser'} );
})
.service( 'bindFields', function(){
	return function getField( $scope, record, names ) {
		angular.forEach( names, function( name ){
			Object.defineProperty( $scope, name, {
				get: function() {
					return record.get( name );
				},
				set: function( newValue ) {
					if( newValue === undefined ) {
						return;
					}
					record.set( name, newValue );
				}
			});
		});

		record.subscribe(function() {
			if( !$scope.$$phase ) {
				$scope.$apply();
			}
		});
	};
})
.controller( 'question', function( deepstream, $scope, bindFields ) {

	var fields = [
            'title',
			'answers'
		];
	var record = deepstream.record.getAnonymousRecord();

	bindFields( $scope, record, fields );

	$scope.$root.$on( 'show-question', function( event, recordName ) {
		$scope.name = recordName;
		record.setName( recordName );
	});
})
.controller( 'questions', function( $scope, deepstream ){
	var list = deepstream.record.getList( 'questions' );
	$scope.questions = [];

	list.subscribe(function( entries ){
		function scopeApply() {
			if( !$scope.$$phase ) {
				$scope.$apply();
			}
		}
		$scope.questions = entries.map(function( entry ){
			var record = deepstream.record.getRecord( entry );
			record.subscribe( scopeApply );
			return record;
		});

		scopeApply();
	});

	$scope.addQuestion = function() {
		var name = 'questions/' + deepstream.getUid();

		deepstream
			.record
			.getRecord( name )
			.set({
				title: 'New Question',
				answers: [
					{'number' : 10, 'text' : 'testext'}
				]
			});

		list.addEntry( name );
	};

	$scope.selectQuestion = function( recordName ) {
		$scope.selectedQuestion = recordName;
		$scope.$root.$emit( 'show-question', recordName );
	};

	$scope.deleteQuestion = function( recordName ) {
		list.removeEntry( recordName );
		deepstream.record.getRecord( recordName ).delete();
	};
});
