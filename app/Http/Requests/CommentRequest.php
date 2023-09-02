<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class CommentRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    
    public function rules()
    {
        return [
            'commentText' => 'nullable|string|max:500',
        ];
    }
}
