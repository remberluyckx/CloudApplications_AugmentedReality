/* global deepstream, angular */


angular
.module( 'ar-webend', ["xeditable", "chart.js"] )
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

})
.controller( 'results', function( $scope, deepstream, bindFields ){
    record = deepstream.record.getRecord('results');
    window.skoop = $scope;
    
    var fields = [
            'title',
			'answer1votes',
            'answer2votes',
            'answer3votes',
            'answer4votes',
            'answer5votes',
            'active',
			'answer1',
            'answer2',
            'answer3',
            'answer4',
            'answer5',
		];
    
    bindFields( $scope, record, fields );

	$scope.labels = ["Antwoord A", "Antwoord B", "Antwoord C", "Antwoord D", "Antwoord E"];
	var piecanvas;

    function updatedata(xxx) {
        $scope.data = [
            $scope.answer1votes,
            $scope.answer2votes,
            $scope.answer3votes,
            $scope.answer4votes,
            $scope.answer5votes
        ];
		piecanvas = document.getElementById("pie").getContext("2d");
		var myChart = new Chart(piecanvas, {
			type: 'pie',
			data: {
				labels: ["Antwoord A", "Antwoord B", "Antwoord C", "Antwoord D", "Antwoord E"],
				datasets: [{
					data: $scope.data,
					backgroundColor: [
						'#99ff66',
						'#ffe066',
						'#009999',
						'#ff471a',
						'#9999ff',
						'#cc33ff'
					],
					borderColor: [
						'rgba(255,99,132,1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)'
					],
					borderWidth: 0.5
				}]
			},
			options: {
				responsive: false,
				legend: {
					display: true,
					labels: {
						fontColor: 'black'
					}
				}
			}
		});
    }

	$scope.data = [$scope.answer1votes,
		$scope.answer2votes,
		$scope.answer3votes,
		$scope.answer4votes,
		$scope.answer5votes];

    record.subscribe(updatedata);



})
;
