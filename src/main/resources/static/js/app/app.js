angular.module('app', []).controller('CampaignController', function($http) {
    var vm = this; //$scope

    vm.campaign={};

    function refreshData() {
        $http({
            method : 'GET',
            url : 'api/campaign'
        }).then(function success(response) {
            vm.campaigns = response.data;
            console.log(vm.campaigns);
        }, function error(response) {
            console.log('API error ' + response.status);
        });
    }

    vm.addCampaign = function(campaign) {
        $http({
            method : 'POST',
            url : 'api/campaign',
            data : campaign
        }).then(function success(response) {
            refreshData();
            vm.campaign = {};
        }, function error(response) {
            console.log('Data not saved ' + campaign);
        });
    }

    vm.deleteCampaign = function(campaign) {
        console.log(campaign + vm.campaign);
        $http({
            method : 'DELETE',
            url : 'api/campaign/delete/'+campaign.id
        }).then(function success(response) {
            console.log(campaign);
            refreshData();

        }, function error(response) {
            console.log('Data not removed ' + campaign.id);
        });
    };


    vm.appName = 'Campaign Manager';
    refreshData();


});