import { Language } from "./enum/language.enum";

export interface language{
    id?: number;
    lang: Language;
    name: string;
    image :Date,
    active: boolean;
    }