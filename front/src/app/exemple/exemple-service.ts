import { httpResource } from '@angular/common/http';
import { Service } from '@angular/core';
import { Exemple } from './exemple-modele';
import { Pagine } from '../core/model/pagine';

@Service()
export class ExempleService {
  readonly exemples = httpResource<Pagine<Exemple>>(
    () => {
      return { url: 'api/exemples'}
    }, { defaultValue: undefined }
  );
}
