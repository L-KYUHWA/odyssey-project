<template>
  <b-modal
    ref="modal"
    size="lg"
    title="Map location"
    v-model="show">
    <div>
      <h5>Place Name :
        <b-badge variant="light">{{setPlaceName()}}</b-badge>
      </h5>
      <h5>New Address :
        <b-badge variant="light">{{setNewAddress()}}</b-badge>
      </h5>
      <h5>Legacy Address :
        <b-badge variant="light">{{setLegacyAddress()}}</b-badge>
      </h5>
      <h5>Phone Number :
        <b-badge variant="light">{{setPhoneNumber()}}</b-badge>
      </h5>
    </div>
    <vue-kakao-map
      :appKey="appKey"
      :center.sync="center"
      :level.sync="level"
      :libraries="libraries"
      :mapTypeId="mapTypeId"
      :markerInfo="markerInfo"
      @bounds_changed="onMapEvent('bounds_changed', $event)"
      @center_changed="onMapEvent('center_changed', $event)"
      @click="onMapEvent('click', $event)"
      @dblclick="onMapEvent('dblclick', $event)"
      @drag="onMapEvent('drag', $event)"
      @dragend="onMapEvent('dragend', $event)"
      @dragstart="onMapEvent('dragstart', $event)"
      @idle="onMapEvent('idle', $event)"
      @load="onLoad"
      @maptypeid_changed="onMapEvent('maptypeid_changed', $event)"
      @mousemove="onMapEvent('mousemove', $event)"
      @rightclick="onMapEvent('rightclick', $event)"
      @tilesloaded="onMapEvent('tilesloaded', $event)"
      @zoom_changed="onMapEvent('zoom_changed', $event)"
      @zoom_start="onMapEvent('zoom_start', $event)"
      id="detailMap"
      ref="kakaoMap">
    </vue-kakao-map>
  </b-modal>

</template>

<script>
  import VueKakaoMap from './VueKakaoMap';

  export default {
    name: "LocationDetailModal",
    components: {VueKakaoMap},
    data() {
      return {
        show: false,
        selectedItem: null,
        appKey: 'a980d54adf8bad772487124ad061074b',
        center: null,
        level: 3,
        mapTypeId: VueKakaoMap.MapTypeId.NORMAL,
        libraries: [],
        mapObject: null,
        markerInfo: null,
      }
    },
    methods: {
      showModal(selectedItem) {
        this.selectedItem = selectedItem;
        this.show = true;
        this.center = {
          lat: selectedItem.y,
          lng: selectedItem.x
        };
        this.markerInfo = {
          placeName: selectedItem.place_name,
          addressMame: selectedItem.address_name
        };
      },
      onLoad(map) {
        const bounds = map.getBounds();
        const boundsStr = bounds.toString();
        this.mapObject = map;
      },
      onMapEvent(event, params) {
        //doNotThing
      },
      setPlaceName() {
        if (this.selectedItem.place_name) {
          return this.selectedItem.place_name;
        }
        return '';
      },
      setNewAddress() {
        if (this.selectedItem.road_address_name) {
          return this.selectedItem.road_address_name;
        }
        return '';
      },
      setLegacyAddress() {
        if (this.selectedItem.address_name) {
          return this.selectedItem.address_name;
        }
        return '';
      },
      setPhoneNumber() {
        if (this.selectedItem.phone) {
          return this.selectedItem.phone;
        }
        return '';
      }
    }
  }

</script>

<style scoped>
  #detailMap {
    width: 500px;
    height: 400px;
  }
</style>
