import React from 'react';

import HotelSearchForm from '../components/hotelSearchForm.jsx';
import HotelSearchResult from '../components/hotelsList.jsx';
import * as HotelsActions from '../actions/HotelsActions.jsx';
import HotelsStore from '../stores/HotelsStore.jsx';

var HotelSearchPanel = React.createClass({
  getInitialState: function() {
    return {
      hotels: HotelsStore.getAllHotels()
    };
  },
  // update search result

  componentWillMount: function() {
    HotelsStore.on("change", this.getHotels);
  },

  componentWillUnmount: function() {
    HotelsStore.removeListener("change", this.getHotels);
  },

  getHotels: function() {
    this.setState({
      hotels: HotelsStore.getAllHotels(),
    });
  },

  reloadHotels: function(requestJSON) {
    HotelsActions.reloadHotels(requestJSON);
  },

  reloadLocations: function(locationKey) {
    HotelsActions.reloadLocations(locationKey);
  },
  
  render: function () {
    if(this.state.hotels) {
      var SearchResultOption = <HotelSearchResult searchResult={this.state.hotels}></HotelSearchResult>
    } else {
      var SearchResultOption = <div className="circular col-sm-8"></div>
    }
    return(
      <div className="row">
        <HotelSearchForm updateSearchResult = {this.reloadHotels} updateLocations = {this.reloadLocations}></HotelSearchForm>
        {SearchResultOption}
      </div>
    );
  }
});
export default HotelSearchPanel;