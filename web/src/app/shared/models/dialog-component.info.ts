import { Generic } from "./generic";

export type DialogComponentInfo<T> = T extends { dialogInfo: Generic } ? T["dialogInfo"] : any;
