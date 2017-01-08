/* global deepstream, angular */


angular
.module( 'ar-webend', ["xeditable"] )
.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
})
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
			'answer1',
            'answer2',
            'answer3',
            'answer4',
            'answer5',
		];

	$scope.editable = true;

	var record = deepstream.record.getAnonymousRecord();

	bindFields( $scope, record, fields );

	$scope.$root.$on( 'show-question', function( event, recordName ) {
		$scope.name = recordName;
		record.setName( recordName );
	});

	$scope.makeEditable = function() {
		$scope.editable = !$scope.editable;
		console.log("hello");
		console.log($scope.editable);
	}

	/*$scope.blablas = [
		{'number' : 1, 'text' : 'bla 1'},
		{'number' : 2, 'text' : 'bla 2'},
		{'number' : 3, 'text' : 'bla 3'}
	]*/

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
				answer1: 'Answer 1',
				answer2: 'Answer 2'
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

	$scope.makeEditable = function() {
		$scope.editable != $scope.editable;
		console.log("ey");
	}

});
