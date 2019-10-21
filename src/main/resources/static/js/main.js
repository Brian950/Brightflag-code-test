var app = angular.module('bfCodeTest', []);

app.controller('studentCtrl', function($scope, $location, $http) {
	console.log("StudentCtrl loaded.");
	
	$http.get('http://localhost:8080/api/getStudents')
	.then(function(response) {
	    $scope.students = response.data;
	});

});

app.controller('enrollmentCtrl', function($scope, $window, $http){

    // Get a list of available subject names
    $http.get('http://localhost:8080/api/getSubjects')
    .then(function(response){
        $scope.subjects = response.data;
    });
    // Gets lists of students
    $http.get('http://localhost:8080/api/getStudents')
    .then(function(response) {
        $scope.students = response.data;
    });
    // Enroll the selected student
    $scope.enroll = function(select){
        if (typeof select === 'undefined' || typeof select.studentID === 'undefined' || typeof select.subjectID === 'undefined'){
            console.log("Options not selected.");
            return;
        }
        // POST request to enroll student
        $http.post("http://localhost:8080/api/", select).then(
          function successCallback(response) {
            console.log("Success response: " + JSON.stringify(response.data));

            if(response.data.result == "ALREADY_ENROLLED")
                alert("This student is already enrolled in this subject!");
            else if(response.data.result == "ENROLLMENT_SUCCESS")
                $window.location.reload();

          },
          function errorCallback(response) {
            console.log("Failure response: " + JSON.stringify(response.data));
          }
        );
    }

});

