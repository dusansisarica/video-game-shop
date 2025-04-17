declare module 'leaflet.heat' {
    import { LayerOptions } from 'leaflet';

    export interface HeatLayerOptions extends LayerOptions {
        radius?: number;
        blur?: number;
        maxZoom?: number;
        minOpacity?: number;
    }

    export function heatLayer(latlngs: Array<[number, number]>, options?: HeatLayerOptions): any;
}
