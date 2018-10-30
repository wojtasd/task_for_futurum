var app = angular.module("app", []);

// Controller Part
app.controller("CampaignController", function($scope, $http) {


    $scope.campaigns = [];
    $scope.campForm = {
         id: null,
         name:"",
         keywords:"",
         bidAmount: "",
         campaignFund:"",
         status:"",
         town:"",
         radius:""
    };

    // load the data from server
    _refreshCampaignData();

    // HTTP POST/PUT methods for add/edit campaign
    // Call: http://localhost:8080/api/campaign

    $scope.submitCampaign = function() {
        var method = "";
        var url = "";

        if ($scope.campForm.id == null) {
            method = "POST";
            url = 'api/campaign';
            console.log("post method --");
        } else {
            method = "PUT";
            url = 'api/campaign';
            console.log("put method --");
        }

        $http({
            method: method,
            url: url,
            data: $scope.campForm,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);

    };

    $scope.createCampaign = function() {
        _clearFormData();
    }

    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/api/campaign/delete/{id}
    $scope.deleteCampaign = function(campaign) {
        console.log(campaign.id);
        $http({
            method: 'DELETE',
            url: 'api/campaign/delete/' + campaign.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editCampaign = function(campaign) {
        $scope.campForm.id = campaign.id;
        $scope.campForm.name=campaign.name;
        $scope.campForm.keywords=campaign.keywords;
        $scope.campForm.bidAmount=campaign.bidAmount;
        $scope.campForm.campaignFund=campaign.campaignFund;
        $scope.campForm.status=campaign.status;
        $scope.campForm.town=campaign.town;
        $scope.campForm.radius=campaign.radius;
    };

    // Private Method
    // HTTP GET- get all campaigns collection
    // Call: http://localhost:8080/api/campaign
    function _refreshCampaignData() {
        $http({
            method: 'GET',
            url: 'api/campaign'
        }).then(
            function(res) { // success
                $scope.campaigns = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshCampaignData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.campForm.id = null;
        $scope.campForm.name="";
        $scope.campForm.keywords="";
        $scope.campForm.bidAmount= "";
        $scope.campForm.campaignFund="";
        $scope.campForm.status=false;
        $scope.campForm.town="";
        $scope.campForm.radius="";
    };
});