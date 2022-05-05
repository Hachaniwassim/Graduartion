import { Language } from "../enum/language.enum";

export interface languageDTO{
    id: number;
    lang: Language;
    name: string;
    image :string,
    active: boolean;
    }